package com.example.core.infrastructure

import kotlinx.coroutines.*

abstract class BaseSingleInteractor<out Type, in Params> :
    BaseInteractor<Type, Params>() where Type : Any {

    private lateinit var job: Deferred<Result<Throwable, Type>>

    operator fun invoke(params: Params, onResult: suspend (Result<Throwable, Type>) -> Unit = {}) {
        job = GlobalScope.async(Dispatchers.Default) {
            runCatchingSuspendable { run(params) }
        }

        GlobalScope.launch(Dispatchers.Main) {
            onResult(job.await())
        }
    }

    fun cancel() {
        job.cancel()
    }

    fun isCanceled(): Boolean {
        return ::job.isInitialized && job.isCancelled
    }

    abstract suspend fun run(params: Params): Type
}