package com.fionicholas.moviecatalogue.core.utils

import okhttp3.Authenticator
import okhttp3.CertificatePinner
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    private const val DEFAULT_MAX_REQUEST = 30

    fun create(authenticator: Authenticator?, showDebugLog: Boolean): OkHttpClient {

        val hostname = AppConstants.HOST_NAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val builder = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .certificatePinner(certificatePinner)

        authenticator?.let {
            builder.authenticator(it)
        }

        if (showDebugLog) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor).build()
        }

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = DEFAULT_MAX_REQUEST
        builder.dispatcher(dispatcher)

        return builder.build()
    }
}