package com.fionicholas.moviecatalogue.core.data.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fionicholas.moviecatalogue.core.data.favorite.local.MoviesDao
import com.fionicholas.moviecatalogue.core.data.favorite.local.TvShowsDao
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.MovieEntity
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.TvShowEntity

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TvShowsDao
}