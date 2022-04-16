package com.fionicholas.moviecatalogue.core.utils.network

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.SocketTimeoutException

fun <T> genericErrorHandler(e: Throwable, result: MutableLiveData<Result<T>>) {
    when (e) {
        is ApiException -> result.value = Result.fail(e, e.apiError.message)
        is SocketTimeoutException -> result.value = Result.fail(e, "Connection Timeout")
        is IOException -> result.value = Result.fail(e, "Connection IOException")
        is JsonSyntaxException -> result.value = Result.fail(e, "JSON Exception")
        else -> result.value = Result.fail(e, "An unknown error occurred")
    }
}