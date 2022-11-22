package com.vendhan.kmmmovies.viewmodel.details

sealed class DetailsScreenSideEffects {
    data class GetMovieDetails(val id: Int) : DetailsScreenSideEffects()
}
