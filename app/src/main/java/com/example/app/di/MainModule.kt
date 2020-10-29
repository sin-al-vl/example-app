package com.example.app.di

import com.example.app.mvi.domain.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(emptyList()) }
}