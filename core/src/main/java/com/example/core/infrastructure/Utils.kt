package com.example.core.infrastructure

internal fun <R> runCatching(block: () -> R): Result<Throwable, R> {
    return try {
        Result.Value(block())
    } catch (e: Throwable) {
        Result.Error(e)
    }
}

internal suspend fun <R> runCatchingSuspendable(block: suspend () -> R): Result<Throwable, R> {
    return try {
        Result.Value(block())
    } catch (e: Throwable) {
        Result.Error(e)
    }
}