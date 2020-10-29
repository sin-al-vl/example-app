package com.example.feature_counter.mvi.domain

import com.example.core.mvi.domain.BaseViewModel
import com.example.core.mvi.domain.Reducer
import com.example.feature_counter.mvi.CounterIntent
import com.example.feature_counter.mvi.view.CounterState
import com.example.feature_counter.navigation.CounterNavigator

class CounterViewModel(
    reducers: List<Reducer<CounterResult, CounterState>>,
    private val counterNavigator: CounterNavigator
) : BaseViewModel<CounterState, CounterIntent, CounterResult>(reducers) {

    override suspend fun processIntent(intent: CounterIntent) {
        when (intent) {
            is CounterIntent.AppTypeIntent -> processAppTypeIntent(intent.isInstantApp)
            is CounterIntent.CounterButtonIntent -> processCounterButtonIntent()
            is CounterIntent.OpenRandomizerIntent -> processOpenRandomizerIntent()
        }
    }

    private suspend fun processAppTypeIntent(isInstantApp: Boolean) {
        resultChannel.send(CounterResult.CounterAppTypeResult(isInstantApp))
    }

    private suspend fun processCounterButtonIntent() {
        resultChannel.send(CounterResult.CounterButtonResult)
    }

    private fun processOpenRandomizerIntent() {
        counterNavigator.openRandomizerScreen()
    }
}