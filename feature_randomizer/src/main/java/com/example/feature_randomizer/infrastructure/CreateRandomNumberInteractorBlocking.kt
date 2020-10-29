package com.example.feature_randomizer.infrastructure

import com.example.core.infrastructure.BaseBlockingInteractor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class CreateRandomNumberInteractorBlocking : BaseBlockingInteractor<Int, Unit>() {

    override fun run(params: Unit) = runBlocking {
        delay(RANDOM_GENERATION_DELAY_MILLIS)
        return@runBlocking Random.nextInt()
    }

    companion object {
        private const val RANDOM_GENERATION_DELAY_MILLIS = 1000L
    }
}