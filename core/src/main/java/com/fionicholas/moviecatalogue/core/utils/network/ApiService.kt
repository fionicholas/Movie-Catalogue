package com.fionicholas.moviecatalogue.core.utils.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    fun <S> createReactiveService(serviceClass: Class<S>, okhttpClient: OkHttpClient, baseURl: String): S {

        GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .client(okhttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}