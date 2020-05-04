package com.doubtnut.amateur.core.responsewrapper

import androidx.annotation.Nullable
import retrofit2.HttpException
import retrofit2.Response

object ApiResponseUtil {

    inline fun <T> asSafeResultWrapper(getApiResponse: () -> ApiResponse<T>): ResultWrapper<T> {
        return try {
            getApiResponse().asResultWrapper()
        } catch (e: java.lang.Exception) {
            ResultWrapper.Error(e)
        }
    }
}

fun <T> ApiResponse<T>.asResultWrapper(): ResultWrapper<T> = this.data?.let {
    ResultWrapper.Result(
        data
    )
}
    ?: ResultWrapper.Error(this.error)

@Nullable
@org.jetbrains.annotations.Nullable
@Throws(HttpException::class)
fun <T> Response<ApiResponse<T>>.asResultWrapperOrNull() = if (this.isSuccessful)
    this.body()?.asResultWrapper() else throw HttpException(this)


fun <T> Response<ApiResponse<T>>.asSafeResultWrapper(): ResultWrapper<T> {
    return try {
        this.asResultWrapperOrNull() ?: throw HttpException(this)
    } catch (e: Exception) {
        ResultWrapper.Error(e)
    }
}