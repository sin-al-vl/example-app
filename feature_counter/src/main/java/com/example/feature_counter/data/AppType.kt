package com.example.feature_counter.data

sealed class AppType {

    object Instant : AppType()
    object Common : AppType()
}