package com.example.feature_randomizer.mvi.view

import com.example.core.mvi.view.State

data class RandomizerState(
    val randomValue: Int?,
    val progress: Boolean
) : State