package com.example.core.infrastructure

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

abstract class BasePublisherInteractor<out Type, in Params> :
    BaseInteractor<Type, Params>() where Type : Any {

    private var job: Job? = null

    @ExperimentalCoroutinesApi
    operator fun invoke(
        params: Params,
        onResult: suspend (Type) -> Unit = {},
        onComplete: suspend (error: Throwable?) -> Unit = {}
    ) {
        job = GlobalScope.launch(Dispatchers.Default) {
            var error: Throwable? = null
            try {
                run(params).consumeEach {
                    withContext(Dispatchers.Main) {
                        onResult.invoke(it)
                    }
                }
            } catch (e: Throwable) {
                error = e
            } finally {
                GlobalScope.launch(Dispatchers.Main) {
                    onComplete.invoke(error)
                }
            }
        }
    }

    open fun cancel() {
        job?.cancel()
    }

    fun isCanceled(): Boolean {
        return job?.isCancelled ?: false
    }

    fun isActive(): Boolean {
        return job?.isActive ?: false
    }

    abstract suspend fun run(params: Params): ReceiveChannel<Type>
}