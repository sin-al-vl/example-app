package com.example.feature_counter.mvi.domain

import com.example.core.mvi.domain.Result

sealed class CounterResult : Result {

    object CounterButtonResult : CounterResult()

    class CounterAppTypeResult(val isInstantApp: Boolean) : CounterResult()
}