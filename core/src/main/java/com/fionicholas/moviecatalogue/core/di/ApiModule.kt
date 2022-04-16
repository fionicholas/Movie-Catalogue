package com.fionicholas.moviecatalogue.core.di

import com.fionicholas.moviecatalogue.core.utils.AppConstants
import com.fionicholas.moviecatalogue.core.utils.OkHttpClientFactory
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named

const val BASE_URL: String = "baseUrl"

val apiModule = org.koin.dsl.module {

    single {
        return@single OkHttpClientFactory.create(
            showDebugLog = BuildConfig.DEBUG,
            authenticator = null
        )
    }

    single(named(BASE_URL)) { AppConstants.BASE_URL }

}