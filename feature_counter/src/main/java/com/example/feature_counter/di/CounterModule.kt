package com.example.feature_counter.di

import com.example.feature_counter.mvi.domain.reducer.AppTypeReducer
import com.example.feature_counter.mvi.domain.reducer.CounterButtonReducer
import com.example.feature_counter.mvi.domain.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val counterModule = module {

    viewModel { CounterViewModel(get(named<CounterViewModel>()), get()) }

    factory(named<CounterViewModel>()) {
        listOf(
            CounterButtonReducer(),
            AppTypeReducer()
        )
    }
}