package com.example.feature_counter.mvi.domain.reducer

import com.example.core.mvi.domain.Reducer
import com.example.feature_counter.mvi.domain.CounterResult
import com.example.feature_counter.mvi.view.CounterState

class CounterButtonReducer : Reducer<CounterResult.CounterButtonResult, CounterState>(
    CounterResult.CounterButtonResult::class
) {

    override fun reduce(
        state: CounterState?,
        result: CounterResult.CounterButtonResult
    ): CounterState = state?.copy(counter = state.counter + 1) ?: CounterState()
}
