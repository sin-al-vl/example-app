package com.example.feature_randomizer.mvi.domain.reducer

import com.example.core.mvi.domain.Reducer
import com.example.feature_randomizer.mvi.domain.RandomizerResult
import com.example.feature_randomizer.mvi.view.RandomizerState

class RandomizerButtonReducer : Reducer<RandomizerResult.RandomizerButtonResult, RandomizerState>(
    RandomizerResult.RandomizerButtonResult::class
) {

    override fun reduce(
        state: RandomizerState?,
        result: RandomizerResult.RandomizerButtonResult
    ) = state?.copy(randomValue = result.randomValue) ?: RandomizerState(result.randomValue, false)
}