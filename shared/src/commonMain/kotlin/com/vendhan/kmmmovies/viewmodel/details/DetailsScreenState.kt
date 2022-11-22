package com.vendhan.kmmmovies.viewmodel.details

import com.vendhan.kmmmovies.repo.model.MoviesResult

data class DetailsScreenState(
    val isLoading: Boolean = true,
    val movies: MoviesResult? = null,
    val error: Error = Error(),
    val isSuccess: Boolean = false,
    val page: Int = 1
)

data class Error(
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong."
)