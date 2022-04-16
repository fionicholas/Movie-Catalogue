package com.fionicholas.moviecatalogue.core.domain.search.model

data class Search(
    val id: Int,
    val mediaType: String,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val title: String,
    val originalLanguage: String,
    val firstAirDate: String,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String
)