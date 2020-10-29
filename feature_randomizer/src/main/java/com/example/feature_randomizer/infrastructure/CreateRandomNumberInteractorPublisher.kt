package com.example.feature_randomizer.infrastructure

import com.example.core.infrastructure.BasePublisherInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlin.random.Random

class CreateRandomNumberInteractorPublisher : BasePublisherInteractor<Int, Unit>() {

    @ExperimentalCoroutinesApi
    override suspend fun run(params: Unit) = GlobalScope.produce {
        repeat(GENERATED_NUMBERS_COUNT) {
            delay(RANDOM_GENERATION_DELAY_MILLIS)
            val randomNumber = Random.nextInt()
            check(randomNumber >= 0) { "Random number is less then null: $randomNumber" }
            send(randomNumber)
        }
    }

    companion object {
        private const val GENERATED_NUMBERS_COUNT = 5
        private const val RANDOM_GENERATION_DELAY_MILLIS = 1000L
    }
}