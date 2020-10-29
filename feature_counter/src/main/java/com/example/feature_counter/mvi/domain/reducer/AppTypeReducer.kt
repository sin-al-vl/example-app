package com.example.feature_counter.mvi.domain.reducer

import com.example.core.mvi.domain.Reducer
import com.example.feature_counter.data.AppType
import com.example.feature_counter.mvi.domain.CounterResult
import com.example.feature_counter.mvi.view.CounterState

class AppTypeReducer : Reducer<CounterResult.CounterAppTypeResult, CounterState>(
    CounterResult.CounterAppTypeResult::class
) {

    override fun reduce(
        state: CounterState?,
        result: CounterResult.CounterAppTypeResult
    ): CounterState {
        val appType = if (result.isInstantApp) AppType.Instant else AppType.Common
        return state?.copy(appType = appType)
            ?: CounterState(appType = appType)
    }
}