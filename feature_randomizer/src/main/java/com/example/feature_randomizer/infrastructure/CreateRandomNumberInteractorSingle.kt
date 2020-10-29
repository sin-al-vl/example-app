package com.example.feature_randomizer.infrastructure

import com.example.core.infrastructure.BaseSingleInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class CreateRandomNumberInteractorSingle : BaseSingleInteractor<Int, Unit>() {

    override suspend fun run(params: Unit) = withContext(Dispatchers.IO) {
        delay(RANDOM_GENERATION_DELAY_MILLIS)
        return@withContext Random.nextInt()
    }

    companion object {
        private const val RANDOM_GENERATION_DELAY_MILLIS = 1000L
    }
}