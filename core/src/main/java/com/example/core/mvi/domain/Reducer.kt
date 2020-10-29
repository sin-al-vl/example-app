package com.example.core.mvi.domain

import com.example.core.mvi.view.State
import kotlin.reflect.KClass

abstract class Reducer<R : Result, S : State>(
    private val resultClass: KClass<R>
) {

    fun couldReduce(result: Result) = result::class == resultClass

    abstract fun reduce(state: S?, result: R): S
}