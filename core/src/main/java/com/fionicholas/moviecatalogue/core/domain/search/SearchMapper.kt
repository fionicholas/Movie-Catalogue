package com.fionicholas.moviecatalogue.core.domain.search

import com.fionicholas.moviecatalogue.core.data.search.model.SearchItem
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow
import com.fionicholas.moviecatalogue.core.domain.search.model.Search
import com.fionicholas.moviecatalogue.core.utils.ext.orZero

fun SearchItem.toDomain(): Search {
    return Search(
        id = id.orZero(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        mediaType = mediaType.orEmpty(),
        name = name.orEmpty(),
        firstAirDate = firstAirDate.orEmpty()
    )
}

fun Search.toMovie(): Movie {
    return Movie(
        id = id.orZero(),
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath
    )
}

fun Search.toTvShow(): TvShow {
    return TvShow(
        id = id.orZero(),
        title = name,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = firstAirDate,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath
    )
}