package com.example.core.mvi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.core.mvi.domain.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseFragment<S : State, VM : BaseViewModel<S, *, *>> : Fragment() {

    @Suppress("LeakingThis")
    val viewModel by viewModel(vmClass())

    protected abstract val layoutResId: Int
        @LayoutRes
        get

    protected abstract fun vmClass(): KClass<VM>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutResId, container, false)
        viewModel.state.observe(viewLifecycleOwner, Observer<S> { state ->
            Timber.d("render: $state")
            render(state)
        })
        return view
    }

    abstract fun render(state: S)
}