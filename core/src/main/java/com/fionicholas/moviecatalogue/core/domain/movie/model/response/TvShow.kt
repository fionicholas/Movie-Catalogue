package com.fionicholas.moviecatalogue.core.domain.movie.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double?,
    val releaseDate: String,
    val originalLanguage: String,
    val backdropPath: String
) : Parcelable