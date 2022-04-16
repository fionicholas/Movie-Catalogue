package com.fionicholas.moviecatalogue.core.domain.movie

import com.fionicholas.moviecatalogue.core.data.movie.MovieRepository
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import io.reactivex.Single

class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {

    override fun getMovies(apiKey: String, language: String): Single<List<Movie>> {
        return repository.getMovies(apiKey, language)
            .map {
                it.map { movieItem ->
                    movieItem.toDomain()
                }
            }
    }

    override fun getTvShows(apiKey: String, language: String): Single<List<TvShow>> {
        return repository.getTvShows(apiKey, language)
            .map {
                it.map { tvShowItem ->
                    tvShowItem.toDomain()
                }
            }
    }
}