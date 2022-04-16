package com.fionicholas.moviecatalogue.core.domain.favorite

import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteUseCase {

    fun getFavoriteMovies(): Single<List<Movie>>
    fun getFavoriteTvShows(): Single<List<TvShow>>

    fun getFavoriteMovieById(id: Int): Single<Movie>
    fun getFavoriteTvShowById(id: Int): Single<TvShow>

    fun insertFavoriteMovie(movie: Movie): Completable
    fun insertFavoriteTvShow(tvShow: TvShow): Completable

    fun deleteFavoriteMovie(id: Int): Completable
    fun deleteFavoriteTvShow(id: Int): Completable
}