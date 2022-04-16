package com.fionicholas.moviecatalogue.core.data.favorite

import com.fionicholas.moviecatalogue.core.data.favorite.local.MoviesDao
import com.fionicholas.moviecatalogue.core.data.favorite.local.TvShowsDao
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.MovieEntity
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.TvShowEntity
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteDataStore(private val moviesDao: MoviesDao, private val tvShowsDao: TvShowsDao) : FavoriteRepository {

    override fun getFavoriteMovies(): Single<List<MovieEntity>> {
        return moviesDao.getFavoriteMovie()
    }

    override fun getFavoriteTvShows(): Single<List<TvShowEntity>> {
        return tvShowsDao.getFavoriteTvShow()
    }

    override fun getFavoriteMovieById(id: Int): Single<MovieEntity> {
        return moviesDao.getFavoriteMovieById(id)
    }

    override fun getFavoriteTvShowById(id: Int): Single<TvShowEntity> {
        return tvShowsDao.getFavoriteTvShowById(id)
    }

    override fun insertFavoriteMovie(movie: MovieEntity): Completable {
        return Completable.fromAction {
            moviesDao.insertFavoriteMovie(movie)
        }
    }

    override fun insertFavoriteTvShow(tvShowEntity: TvShowEntity): Completable {
        return Completable.fromAction {
            tvShowsDao.insertFavoriteTvShow(tvShowEntity)
        }
    }

    override fun deleteFavoriteMovie(id: Int): Completable {
        return Completable.fromAction {
            moviesDao.deleteFavoriteMovie(id)
        }
    }

    override fun deleteFavoriteTvShow(id: Int): Completable {
        return Completable.fromAction {
            tvShowsDao.deleteFavoriteTvShow(id)
        }
    }
}