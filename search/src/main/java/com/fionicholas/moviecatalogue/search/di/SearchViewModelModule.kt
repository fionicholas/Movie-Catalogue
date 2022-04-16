package com.fionicholas.moviecatalogue.search.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchViewModelModule = module {

    viewModel {
        com.fionicholas.moviecatalogue.search.presentation.SearchViewModel(
            get(),
            get()
        )
    }
}