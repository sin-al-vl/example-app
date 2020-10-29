package com.example.feature_randomizer.mvi

import com.example.core.mvi.Intent

sealed class RandomizerIntent : Intent {

    data class RandomizerButtonIntent(val interactorType: Int) : RandomizerIntent()

    companion object {
        const val BLOCKING_INTERACTOR = 0
        const val SINGLE_INTERACTOR = 1
        const val PUBLISHER_INTERACTOR = 2
    }
}