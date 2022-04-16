package com.fionicholas.moviecatalogue.core.data.favorite.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.TvShowEntity
import io.reactivex.Single

@Dao
interface TvShowsDao {

    @Query("SELECT * FROM tv_show_favorite ORDER BY id ASC")
    fun getFavoriteTvShow(): Single<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show_favorite WHERE id=:idMovie")
    fun getFavoriteTvShowById(idMovie: Int): Single<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteTvShow(movies: TvShowEntity)

    @Query("DELETE FROM tv_show_favorite WHERE id=:idMovie")
    fun deleteFavoriteTvShow(idMovie: Int)
}