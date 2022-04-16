package com.fionicholas.moviecatalogue.core.domain.favorite

import com.fionicholas.moviecatalogue.core.data.favorite.model.response.MovieEntity
import com.fionicholas.moviecatalogue.core.data.favorite.model.response.TvShowEntity
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.Movie
import com.fionicholas.moviecatalogue.core.domain.movie.model.response.TvShow

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath
    )
}

fun TvShowEntity.toDomain(): TvShow {
    return TvShow(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath
    )
}

fun TvShow.toEntity(): TvShowEntity {
    return TvShowEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath
    )
}