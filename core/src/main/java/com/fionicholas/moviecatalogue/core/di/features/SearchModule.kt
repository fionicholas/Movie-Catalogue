package com.fionicholas.moviecatalogue.core.di.features

import com.fionicholas.moviecatalogue.core.data.search.SearchDataStore
import com.fionicholas.moviecatalogue.core.data.search.SearchRepository
import com.fionicholas.moviecatalogue.core.data.search.remote.SearchApi
import com.fionicholas.moviecatalogue.core.data.search.remote.SearchApiClient
import com.fionicholas.moviecatalogue.core.di.BASE_URL
import com.fionicholas.moviecatalogue.core.domain.search.SearchInteractor
import com.fionicholas.moviecatalogue.core.domain.search.SearchUseCase
import com.fionicholas.moviecatalogue.core.utils.network.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val searchModule = module {

    single {
        ApiService.createReactiveService(
            SearchApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }

    single { SearchApi(get()) }

    single<SearchRepository> { SearchDataStore(get()) }

    single<SearchUseCase> { SearchInteractor(get()) }
}