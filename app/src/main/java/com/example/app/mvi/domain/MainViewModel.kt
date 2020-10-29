package com.example.app.mvi.domain

import com.example.core.mvi.domain.BaseViewModel
import com.example.core.mvi.domain.Reducer
import com.example.app.mvi.MainIntent
import com.example.app.mvi.view.MainState

class MainViewModel(
    reducers: List<Reducer<MainResult, MainState>>
) : BaseViewModel<MainState, MainIntent, MainResult>(
    reducers
) {

    override suspend fun processIntent(intent: MainIntent) {
        // none
    }
}