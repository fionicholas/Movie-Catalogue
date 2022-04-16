package com.fionicholas.moviecatalogue.core.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("results")
    val results: List<TvShowItem>
)

data class TvShowItem(
    @SerializedName("id") val id: Int?,
    @SerializedName("original_name") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("first_air_date") val releaseDate: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)