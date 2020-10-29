package com.example.core.mvi.view

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.core.mvi.domain.BaseViewModel
import com.example.core.navigation.NavControllerProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseActivity<S : State, VM : BaseViewModel<S, *, *>> : AppCompatActivity() {

    @Suppress("LeakingThis")
    val viewModel by viewModel(vmClass())

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    @IdRes
    abstract fun getNavControllerHostFragmentId(): Int

    protected abstract fun vmClass(): KClass<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        initNavControllerProvider()

        observeState()
    }

    private fun initNavControllerProvider() {
        lifecycle.addObserver(
            NavControllerProvider(
                createNavController(getNavControllerHostFragmentId())
            )
        )
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer<S> { state ->
            Timber.d("render: $state")
            render(state)
        })
    }

    private fun createNavController(
        @IdRes viewId: Int
    ) = Navigation.findNavController(this, viewId)

    abstract fun render(state: S)
}