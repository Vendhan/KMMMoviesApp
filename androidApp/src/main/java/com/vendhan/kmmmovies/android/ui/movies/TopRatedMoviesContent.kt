package com.vendhan.kmmmovies.android.ui.movies

import androidx.compose.runtime.Composable
import com.vendhan.kmmmovies.repo.model.MoviesResult

@Composable
fun TopRatedMoviesContent(
    title: String,
    movies: List<MoviesResult>,
    onClick: (Int) -> Unit
) {
    MoviesHeader(
        title = title
    )
    movies.forEach { movie ->
        MoviesCardHorizontal(
            movie = movie,
            onClick = onClick
        )
    }
}
