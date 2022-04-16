package com.fionicholas.moviecatalogue

import com.fionicholas.moviecatalogue.core.base.BaseApplication
import com.fionicholas.moviecatalogue.core.di.apiModule
import com.fionicholas.moviecatalogue.core.di.dbModule
import com.fionicholas.moviecatalogue.core.di.features.favoriteModule
import com.fionicholas.moviecatalogue.core.di.features.movieModule
import com.fionicholas.moviecatalogue.core.di.features.searchModule
import com.fionicholas.moviecatalogue.core.di.utilityModule
import com.fionicholas.moviecatalogue.di.appModule
import org.koin.core.module.Module

class MainApplication : BaseApplication() {

    override fun getDefinedModules(): List<Module> {
        return listOf(
            utilityModule,
            apiModule,
            dbModule,
            movieModule,
            favoriteModule,
            searchModule,
            appModule
        )
    }
}