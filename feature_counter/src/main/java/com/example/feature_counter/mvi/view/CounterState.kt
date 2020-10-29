package com.example.feature_counter.mvi.view

import com.example.core.mvi.view.State
import com.example.feature_counter.data.AppType

data class CounterState(
    val appType: AppType = AppType.Common,
    val counter: Int = 0
) : State