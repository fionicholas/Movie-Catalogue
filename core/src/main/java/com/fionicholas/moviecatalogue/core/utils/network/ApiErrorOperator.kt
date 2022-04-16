package com.fionicholas.moviecatalogue.core.utils.network

import com.fionicholas.moviecatalogue.core.utils.rx.SingleApiErrorOperator
import com.google.gson.Gson

fun <T> singleApiError(): SingleApiErrorOperator<T> {
    return SingleApiErrorOperator(
        Gson()
    )
}