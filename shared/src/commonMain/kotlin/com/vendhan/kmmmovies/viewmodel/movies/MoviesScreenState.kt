package com.vendhan.kmmmovies.viewmodel.movies

import com.vendhan.kmmmovies.repo.model.MoviesResult

data class MoviesScreenState(
    val isLoading: Boolean = true,
    val movies: List<MoviesResult> = emptyList(),
    val error: Error = Error(),
    val isSuccess: Boolean = false,
    val page: Int = 1
)

data class Error(
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong."
)
