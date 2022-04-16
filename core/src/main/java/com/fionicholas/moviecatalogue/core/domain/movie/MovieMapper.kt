package com.fionicholas.moviecatalogue.core.domain.movie

import com.fionicholas.moviecatalogue.core.data.movie.model.response.MovieItem
import com.fionicholas.moviecatalogue.core.data.movie.model.response.TvShowItem
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.utils.ext.orZero

fun MovieItem.toDomain(): Movie {
    return Movie(
        id = id.orZero(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage,
        releaseDate = releaseDate.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        backdropPath = backdropPath.orEmpty()
    )
}

fun TvShowItem.toDomain(): TvShow {
    return TvShow(
        id = id.orZero(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage,
        releaseDate = releaseDate.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        backdropPath = backdropPath.orEmpty()
    )
}