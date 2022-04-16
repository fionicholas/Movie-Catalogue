package com.fionicholas.moviecatalogue.core.di.features

import com.fionicholas.moviecatalogue.core.data.movie.MovieDataStore
import com.fionicholas.moviecatalogue.core.data.movie.MovieRepository
import com.fionicholas.moviecatalogue.core.data.movie.remote.MovieApi
import com.fionicholas.moviecatalogue.core.data.movie.remote.MovieApiClient
import com.fionicholas.moviecatalogue.core.di.BASE_URL
import com.fionicholas.moviecatalogue.core.domain.movie.MovieInteractor
import com.fionicholas.moviecatalogue.core.domain.movie.MovieUseCase
import com.fionicholas.moviecatalogue.core.utils.network.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {

    single {
        ApiService.createReactiveService(
            MovieApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }

    single { MovieApi(get()) }

    single<MovieRepository> { MovieDataStore(get()) }

    single<MovieUseCase> { MovieInteractor(get()) }
}