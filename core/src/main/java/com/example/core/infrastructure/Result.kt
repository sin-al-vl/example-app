package com.example.core.infrastructure

sealed class Result<out E, out V> {

    data class Error<out E>(val error: E) : Result<E, Nothing>()

    data class Value<out V>(val value: V) : Result<Nothing, V>()

    val isValue get() = this is Value<V>

    val isError get() = this is Error<E>

    fun <E> error(e: E) = Error(e)

    fun <V> value(v: V) = Value(v)

    fun fold(fnE: (E) -> Any, fnV: (V) -> Any): Any =
        when (this) {
            is Error -> fnE(error)
            is Value -> fnV(value)
        }

    suspend fun suspendFold(
        fnE: suspend (E) -> Any,
        fnV: suspend (V) -> Any
    ): Any = when (this) {
        is Error -> fnE(error)
        is Value -> fnV(value)
    }

    fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
        f(this(it))
    }

    fun <T, L, R> Result<L, R>.flatMap(fn: (R) -> Result<L, T>): Result<L, T> =
        when (this) {
            is Error -> Error(error)
            is Value -> fn(value)
        }

    fun <T, L, R> Result<L, R>.map(fn: (R) -> (T)): Result<L, T> = this.flatMap(fn.c(::value))

    fun getValueOrNull() = if (this is Value) value else null

    fun getValueOrThrow() =
        if (this is Value)
            value
        else
            throw IllegalStateException("The value is unavailable for Result: $this")

    fun getErrorOrNull() = if (this is Error) error else null

    fun getErrorOrThrow() =
        if (this is Error)
            error
        else
            throw IllegalStateException("The error is unavailable for Result: $this")
}