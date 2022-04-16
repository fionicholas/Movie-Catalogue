package com.fionicholas.moviecatalogue.core.data.movie

import com.fionicholas.moviecatalogue.core.data.movie.model.response.MovieItem
import com.fionicholas.moviecatalogue.core.data.movie.model.response.TvShowItem
import com.fionicholas.moviecatalogue.core.utils.network.BaseRepository
import io.reactivex.Single

interface MovieRepository : BaseRepository {

    fun getMovies(apiKey: String, language: String): Single<List<MovieItem>>
    fun getTvShows(apiKey: String, language: String): Single<List<TvShowItem>>
}