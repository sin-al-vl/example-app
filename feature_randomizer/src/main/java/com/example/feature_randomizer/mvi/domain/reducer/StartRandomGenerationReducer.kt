package com.example.feature_randomizer.mvi.domain.reducer

import com.example.core.mvi.domain.Reducer
import com.example.feature_randomizer.mvi.domain.RandomizerResult
import com.example.feature_randomizer.mvi.view.RandomizerState

class StartRandomGenerationReducer :
    Reducer<RandomizerResult.StartRandomGenerationResult, RandomizerState>(
        RandomizerResult.StartRandomGenerationResult::class
    ) {

    override fun reduce(
        state: RandomizerState?,
        result: RandomizerResult.StartRandomGenerationResult
    ) = state?.copy(progress = true) ?: RandomizerState(null, true)
}