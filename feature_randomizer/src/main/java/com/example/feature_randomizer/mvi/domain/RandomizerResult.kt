package com.example.feature_randomizer.mvi.domain

import com.example.core.mvi.domain.Result

sealed class RandomizerResult : Result {

    object StartRandomGenerationResult : RandomizerResult()

    object FinishRandomGenerationResult : RandomizerResult()

    data class RandomizerButtonResult(val randomValue: Int) : RandomizerResult()
}
