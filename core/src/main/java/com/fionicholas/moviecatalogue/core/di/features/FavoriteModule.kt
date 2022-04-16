package com.fionicholas.moviecatalogue.core.di.features

import com.fionicholas.moviecatalogue.core.data.favorite.FavoriteDataStore
import com.fionicholas.moviecatalogue.core.data.favorite.FavoriteRepository
import com.fionicholas.moviecatalogue.core.domain.favorite.FavoriteInteractor
import com.fionicholas.moviecatalogue.core.domain.favorite.FavoriteUseCase
import com.fionicholas.moviecatalogue.core.presentation.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    single<FavoriteRepository> { FavoriteDataStore(get(), get()) }

    single<FavoriteUseCase> { FavoriteInteractor(get()) }

    viewModel {
        FavoriteViewModel(
            get(),
            get()
        )
    }

}