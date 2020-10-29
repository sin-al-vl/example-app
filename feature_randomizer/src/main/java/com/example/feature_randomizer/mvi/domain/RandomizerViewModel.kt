package com.example.feature_randomizer.mvi.domain

import com.example.core.mvi.domain.BaseViewModel
import com.example.core.mvi.domain.Reducer
import com.example.feature_randomizer.infrastructure.CreateRandomNumberInteractorBlocking
import com.example.feature_randomizer.infrastructure.CreateRandomNumberInteractorPublisher
import com.example.feature_randomizer.infrastructure.CreateRandomNumberInteractorSingle
import com.example.feature_randomizer.mvi.RandomizerIntent
import com.example.feature_randomizer.mvi.view.RandomizerState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class RandomizerViewModel(
    reducers: List<Reducer<RandomizerResult, RandomizerState>>,
    private val createRandomNumberInteractorBlocking: CreateRandomNumberInteractorBlocking,
    private val createRandomNumberInteractorSingle: CreateRandomNumberInteractorSingle,
    private val createRandomnumberinteractorPublisher: CreateRandomNumberInteractorPublisher
) : BaseViewModel<RandomizerState, RandomizerIntent, RandomizerResult>(
    reducers
) {

    override suspend fun processIntent(intent: RandomizerIntent) {
        when (intent) {
            is RandomizerIntent.RandomizerButtonIntent -> processRandomizerButtonIntent(intent)
        }
    }

    private suspend fun processRandomizerButtonIntent(
        intent: RandomizerIntent.RandomizerButtonIntent
    ) {
        resultChannel.send(RandomizerResult.StartRandomGenerationResult)

        when (intent.interactorType) {
            RandomizerIntent.BLOCKING_INTERACTOR -> generateRandomNumberBlocking()
            RandomizerIntent.SINGLE_INTERACTOR -> generateRandomNumberAsync()
            RandomizerIntent.PUBLISHER_INTERACTOR -> generateRandomNumbersPublisher()
        }
    }

    private suspend fun generateRandomNumberBlocking() {
        val randomNumberResult = createRandomNumberInteractorBlocking(Unit)
        randomNumberResult.getValueOrNull()?.also { randomNumber ->
            onRandomNumberGenerated(randomNumber)
        } ?: onRandomNumberGenerationError(randomNumberResult.getErrorOrThrow())

        resultChannel.send(RandomizerResult.FinishRandomGenerationResult)
    }

    private fun generateRandomNumberAsync() {
        createRandomNumberInteractorSingle(Unit) { randomNumberResult ->
            randomNumberResult.getValueOrNull()?.also { randomNumber ->
                onRandomNumberGenerated(randomNumber)
                resultChannel.send(RandomizerResult.FinishRandomGenerationResult)
            } ?: onRandomNumberGenerationError(randomNumberResult.getErrorOrThrow())
        }
    }

    @ExperimentalCoroutinesApi
    private fun generateRandomNumbersPublisher() {
        createRandomnumberinteractorPublisher(
            Unit,
            ::onRandomNumberGenerated,
            ::onRandomNumberGenerationComplete
        )
    }

    private suspend fun onRandomNumberGenerated(randomNumber: Int) {
        val result = RandomizerResult.RandomizerButtonResult(randomNumber)
        resultChannel.send(result)
    }

    private suspend fun onRandomNumberGenerationError(throwable: Throwable) {
        Timber.e(throwable, "Random error")
        resultChannel.send(RandomizerResult.FinishRandomGenerationResult)
    }

    private suspend fun onRandomNumberGenerationComplete(throwable: Throwable?) {
        if (throwable == null) {
            resultChannel.send(RandomizerResult.FinishRandomGenerationResult)
        } else {
            onRandomNumberGenerationError(throwable)
        }
    }
}