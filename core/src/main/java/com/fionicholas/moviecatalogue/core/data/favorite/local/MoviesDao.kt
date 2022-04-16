package com.fionicholas.moviecatalogue.core.data.favorite.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.MovieEntity
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie_favorite ORDER BY id ASC")
    fun getFavoriteMovie(): Single<List<MovieEntity>>

    @Query("SELECT * FROM movie_favorite WHERE id=:idMovie")
    fun getFavoriteMovieById(idMovie: Int): Single<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(movies: MovieEntity)

    @Query("DELETE FROM movie_favorite WHERE id=:idMovie")
    fun deleteFavoriteMovie(idMovie: Int)
}