package com.fionicholas.moviecatalogue.core.utils.rx

import com.fionicholas.moviecatalogue.core.utils.network.ApiError
import com.fionicholas.moviecatalogue.core.utils.network.ApiException
import com.fionicholas.moviecatalogue.core.utils.network.ResponseException
import com.google.gson.Gson
import io.reactivex.SingleObserver
import io.reactivex.SingleOperator
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.io.IOException

class SingleApiErrorOperator<T>(private val gson: Gson) : SingleOperator<T, Response<T>> {

    override fun apply(observer: SingleObserver<in T>): SingleObserver<in Response<T>> {
        return object : SingleObserver<Response<T>> {

            override fun onSuccess(response: Response<T>) {
                if (!response.isSuccessful) {
                    try {
                        val envelope = gson.fromJson(response.errorBody()!!.string(), ApiError::class.java)
                        observer.onError(
                            ApiException(
                                envelope,
                                response
                            )
                        )
                    } catch (e: IOException) {
                        observer.onError(
                            ResponseException(
                                response
                            )
                        )
                    }

                } else if (response.code() == 204) {
                    val apiError =
                        ApiError(
                            "Data Empty"
                        )
                    observer.onError(
                        ApiException(
                            apiError,
                            response
                        )
                    )
                } else {
                    observer.onSuccess(response.body()!!)
                }
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        }
    }
}
