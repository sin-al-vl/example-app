package com.example.feature_randomizer.mvi.domain.reducer

import com.example.core.mvi.domain.Reducer
import com.example.feature_randomizer.mvi.domain.RandomizerResult
import com.example.feature_randomizer.mvi.view.RandomizerState

class FinishRandomGenerationReducer :
    Reducer<RandomizerResult.FinishRandomGenerationResult, RandomizerState>(
        RandomizerResult.FinishRandomGenerationResult::class
    ) {

    override fun reduce(
        state: RandomizerState?,
        result: RandomizerResult.FinishRandomGenerationResult
    ) = state?.copy(progress = false) ?: RandomizerState(null, false)
}