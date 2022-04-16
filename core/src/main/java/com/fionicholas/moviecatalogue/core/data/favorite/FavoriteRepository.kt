package com.fionicholas.moviecatalogue.core.data.favorite

import com.fionicholas.moviecatalogue.core.data.favorite.model.response.MovieEntity
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.TvShowEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteRepository {

    fun getFavoriteMovies(): Single<List<MovieEntity>>
    fun getFavoriteTvShows(): Single<List<TvShowEntity>>

    fun getFavoriteMovieById(id: Int): Single<MovieEntity>
    fun getFavoriteTvShowById(id: Int): Single<TvShowEntity>

    fun insertFavoriteMovie(movie: MovieEntity): Completable
    fun insertFavoriteTvShow(tvShowEntity: TvShowEntity): Completable

    fun deleteFavoriteMovie(id: Int): Completable
    fun deleteFavoriteTvShow(id: Int): Completable
}