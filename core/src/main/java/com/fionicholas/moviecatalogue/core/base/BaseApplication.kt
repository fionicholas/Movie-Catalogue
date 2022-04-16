package com.fionicholas.moviecatalogue.core.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(getDefinedModules())
        }
    }

    abstract fun getDefinedModules(): List<Module>
}