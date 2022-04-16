package com.fionicholas.moviecatalogue.core.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<MovieItem>
)

data class MovieItem(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)