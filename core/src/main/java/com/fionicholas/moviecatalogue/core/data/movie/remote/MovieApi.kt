package com.fionicholas.moviecatalogue.core.data.movie.remote

import com.fionicholas.moviecatalogue.core.data.movie.model.response.MovieResponse
import com.fionicholas.moviecatalogue.core.data.movie.model.response.TvShowResponse
import com.fionicholas.moviecatalogue.core.utils.network.WebApi
import io.reactivex.Single
import retrofit2.Response

class MovieApi(private val apiClient: MovieApiClient) : WebApi, MovieApiClient {

    override fun getMovies(apiKey: String, language: String): Single<Response<MovieResponse>> {
        return apiClient.getMovies(apiKey, language)
    }

    override fun getTvShows(apiKey: String, language: String): Single<Response<TvShowResponse>> {
        return apiClient.getTvShows(apiKey, language)
    }
}