package com.vendhan.kmmmovies.viewmodel.movies

sealed class MoviesScreenSideEffects {
    object GetNowPlayingMovies : MoviesScreenSideEffects()
    object GetUpcomingMovies : MoviesScreenSideEffects()
    object GetTopRatedMovies : MoviesScreenSideEffects()
}
