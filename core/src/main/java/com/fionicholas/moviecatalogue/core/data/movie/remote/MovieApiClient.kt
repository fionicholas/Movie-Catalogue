package com.fionicholas.moviecatalogue.core.data.movie.remote

import com.fionicholas.moviecatalogue.core.data.movie.model.response.MovieResponse
import com.fionicholas.moviecatalogue.core.data.movie.model.response.TvShowResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<Response<MovieResponse>>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<Response<TvShowResponse>>
}