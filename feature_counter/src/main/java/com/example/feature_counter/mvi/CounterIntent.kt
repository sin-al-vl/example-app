package com.example.feature_counter.mvi

import com.example.core.mvi.Intent

sealed class CounterIntent : Intent {

    class AppTypeIntent(val isInstantApp: Boolean) : CounterIntent()
    object CounterButtonIntent : CounterIntent()
    object OpenRandomizerIntent : CounterIntent()
}