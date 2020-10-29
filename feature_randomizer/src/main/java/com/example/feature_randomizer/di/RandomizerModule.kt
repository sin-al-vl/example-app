package com.example.feature_randomizer.di

import com.example.feature_randomizer.infrastructure.CreateRandomNumberInteractorBlocking
import com.example.feature_randomizer.infrastructure.CreateRandomNumberInteractorPublisher
import com.example.feature_randomizer.infrastructure.CreateRandomNumberInteractorSingle
import com.example.feature_randomizer.mvi.domain.RandomizerViewModel
import com.example.feature_randomizer.mvi.domain.reducer.FinishRandomGenerationReducer
import com.example.feature_randomizer.mvi.domain.reducer.RandomizerButtonReducer
import com.example.feature_randomizer.mvi.domain.reducer.StartRandomGenerationReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val randomizerModule = module {

    viewModel {
        RandomizerViewModel(
            get(named<RandomizerViewModel>()), get(), get(), get()
        )
    }

    factory(named<RandomizerViewModel>()) {
        listOf(
            StartRandomGenerationReducer(),
            FinishRandomGenerationReducer(),
            RandomizerButtonReducer()
        )
    }

    factory { CreateRandomNumberInteractorBlocking() }

    factory { CreateRandomNumberInteractorPublisher() }

    factory { CreateRandomNumberInteractorSingle() }
}