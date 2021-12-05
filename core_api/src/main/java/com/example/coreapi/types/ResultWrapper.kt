package com.example.coreapi.types

sealed class ResultWrapper<out T> {
    data class Data<T>(val value: T) : ResultWrapper<T>()
    data class Loading(val show: Boolean) : ResultWrapper<Nothing>()
    data class Error(val error: Throwable) : ResultWrapper<Nothing>()
}

fun <T> ResultWrapper<T>.unwrap(
    onData: (T) -> Unit = {},
    onLoading: (Boolean) -> Unit = {},
    onError: (Throwable) -> Unit = {}
) {
    when (this) {
        is ResultWrapper.Data -> onData(value)
        is ResultWrapper.Loading -> onLoading(show)
        is ResultWrapper.Error -> onError(error)
    }
}
