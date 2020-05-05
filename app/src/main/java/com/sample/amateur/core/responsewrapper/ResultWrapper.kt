package com.sample.amateur.core.responsewrapper

sealed class ResultWrapper<T>(val success: Boolean) {

    class Result<T>(val data: T) : ResultWrapper<T>(true)

    class Error<T>(val cause: Throwable?) : ResultWrapper<T>(false)
}

fun <T> ResultWrapper<T>.asResult(): ResultWrapper.Result<T> =
    if (this is ResultWrapper.Result<T>) this else error("Not ResultWrapper.Result type")

fun <T> ResultWrapper<T>.asError(): ResultWrapper.Error<T> =
    if (this is ResultWrapper.Error<T>) this else error("Not ResultWrapper.Error type")

