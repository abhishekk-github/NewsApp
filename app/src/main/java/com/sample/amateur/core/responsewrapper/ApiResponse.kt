package com.sample.amateur.core.responsewrapper

import com.google.gson.annotations.SerializedName

class ApiResponse<T>(
    @SerializedName("data")
    val data: T? = null,

    @SerializedName("errors")
    val error: Error? = null
) {


    class Error(@SerializedName("code") val code: Int,
                @SerializedName("message") val errorMessage: String)
        : Exception(errorMessage)
}
