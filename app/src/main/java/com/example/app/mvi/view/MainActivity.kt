package com.example.app.mvi.view

import com.example.core.mvi.view.BaseActivity
import com.example.app.R
import com.example.app.mvi.domain.MainViewModel

class MainActivity : BaseActivity<MainState, MainViewModel>() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun getNavControllerHostFragmentId() = R.id.nav_host_fragment

    override fun vmClass() = MainViewModel::class

    override fun render(state: MainState) {
        // none
    }
}