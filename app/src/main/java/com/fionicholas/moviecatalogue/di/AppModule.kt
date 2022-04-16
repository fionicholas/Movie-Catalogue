package com.fionicholas.moviecatalogue.di

import com.fionicholas.moviecatalogue.presentation.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MovieViewModel(
            get(),
            get()
        )
    }
}