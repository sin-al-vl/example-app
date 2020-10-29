package com.example.core.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

internal class NavControllerProvider(
    private val navController: NavController
) : LifecycleObserver {

    private val navControllerModule = module {
        factory { navController }
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadNavConrollerModule() {
        loadKoinModules(navControllerModule)
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unloadNavControllerModule() {
        unloadKoinModules(navControllerModule)
    }
}