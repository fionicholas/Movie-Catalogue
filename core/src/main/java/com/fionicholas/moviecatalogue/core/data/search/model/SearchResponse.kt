package com.fionicholas.moviecatalogue.core.data.search.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results") val results: List<SearchItem>
)

data class SearchItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)