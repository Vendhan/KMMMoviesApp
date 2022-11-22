package com.vendhan.kmmmovies.repo.model

import com.vendhan.kmmmovies.database.Movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResultWrapper(
    val results: List<MoviesResult>
)

@Serializable
data class MoviesResult(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName(value = "poster_path")
    val posterPath: String?,
    @SerialName(value = "release_date")
    val releaseDate: String,
    @SerialName(value = "vote_average")
    val voteAverage: Double,
    @SerialName(value = "vote_count")
    val voteCount: Int
)

fun Movies.toModel(): MoviesResult {
    return MoviesResult(
        id = id.toInt(),
        title = title,
        overview = overview,
        posterPath = IMAGE_PATH_PREFIX + posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount.toInt()
    )
}

const val IMAGE_PATH_PREFIX = "https://image.tmdb.org/t/p/w1280"
