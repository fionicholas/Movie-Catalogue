package com.fionicholas.moviecatalogue.core.di

import androidx.room.Room
import com.fionicholas.moviecatalogue.core.data.favorite.AppDatabase
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

val dbModule = module {

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("fionicholas".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            AppConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single { get<AppDatabase>().moviesDao() }
    single { get<AppDatabase>().tvShowsDao() }

}