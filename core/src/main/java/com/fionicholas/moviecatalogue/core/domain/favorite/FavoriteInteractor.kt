package com.fionicholas.moviecatalogue.core.domain.favorite

import com.fionicholas.moviecatalogue.core.data.favorite.FavoriteRepository
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteInteractor(private val repository: FavoriteRepository) : FavoriteUseCase {

    override fun getFavoriteMovies(): Single<List<Movie>> {
        return repository.getFavoriteMovies().map {
            it.map { movieEntity ->
                movieEntity.toDomain()
            }
        }
    }

    override fun getFavoriteTvShows(): Single<List<TvShow>> {
        return repository.getFavoriteTvShows().map {
            it.map { tvShowEntity ->
                tvShowEntity.toDomain()
            }
        }
    }

    override fun getFavoriteMovieById(id: Int): Single<Movie> {
        return repository.getFavoriteMovieById(id).map { it.toDomain() }
    }

    override fun getFavoriteTvShowById(id: Int): Single<TvShow> {
        return repository.getFavoriteTvShowById(id).map { it.toDomain() }
    }

    override fun insertFavoriteMovie(movie: Movie): Completable {
        return repository.insertFavoriteMovie(movie.toEntity())
    }

    override fun insertFavoriteTvShow(tvShow: TvShow): Completable {
        return repository.insertFavoriteTvShow(tvShow.toEntity())
    }

    override fun deleteFavoriteMovie(id: Int): Completable {
        return repository.deleteFavoriteMovie(id)
    }

    override fun deleteFavoriteTvShow(id: Int): Completable {
        return repository.deleteFavoriteTvShow(id)
    }
}