package com.example.app.di

import com.example.app.navigation.CounterNavigatorImpl
import com.example.feature_counter.navigation.CounterNavigator
import org.koin.dsl.module

val navigationModule = module {

    factory<CounterNavigator> { CounterNavigatorImpl(get()) }
}
