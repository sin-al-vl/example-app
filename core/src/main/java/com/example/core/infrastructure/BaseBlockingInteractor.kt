package com.example.core.infrastructure

abstract class BaseBlockingInteractor<out Type, in Params> :
    BaseInteractor<Type, Params>() where Type : Any {

    operator fun invoke(params: Params) =
        com.example.core.infrastructure.runCatching { run(params) }

    abstract fun run(params: Params): Type
}