package com.vendhan.kmmmovies.viewmodel.movies

import com.vendhan.kmmmovies.repo.MoviesRepository
import com.vendhan.kmmmovies.util.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var nowPlayingMoviesState = MutableStateFlow(MoviesScreenState())
    var upcomingMoviesState = MutableStateFlow(MoviesScreenState())
    var topRatedMoviesState = MutableStateFlow(MoviesScreenState())

    fun onIntent(intent: MoviesScreenSideEffects) {
        when (intent) {
            is MoviesScreenSideEffects.GetNowPlayingMovies -> {
                getNowPlayingMovies()
            }
            is MoviesScreenSideEffects.GetUpcomingMovies -> {
                getUpcomingMovies()
            }
            is MoviesScreenSideEffects.GetTopRatedMovies -> {
                getTopRatedMovies()
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            moviesRepository
                .fetchNowPlayingMovies()
                .collectLatest { result ->
                    when (result) {
                        is Result.Loading -> nowPlayingMoviesState.emit(
                            MoviesScreenState(
                                isLoading = true
                            )
                        )
                        is Result.Error -> {
                            nowPlayingMoviesState.emit(
                                nowPlayingMoviesState.value.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    error = Error(true, result.message)
                                )
                            )
                        }
                        is Result.Success -> {
                            nowPlayingMoviesState.emit(
                                nowPlayingMoviesState.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    movies = result.data
                                )
                            )
                        }
                    }
                }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            moviesRepository
                .fetchUpcomingMovies()
                .collectLatest { result ->
                    when (result) {
                        is Result.Loading -> upcomingMoviesState.emit(
                            MoviesScreenState(
                                isLoading = true
                            )
                        )
                        is Result.Error -> {
                            upcomingMoviesState.emit(
                                upcomingMoviesState.value.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    error = Error(true, result.message)
                                )
                            )
                        }
                        is Result.Success -> {
                            upcomingMoviesState.emit(
                                upcomingMoviesState.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    movies = result.data
                                )
                            )
                        }
                    }
                }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            moviesRepository
                .fetchTopRatedMovies()
                .collectLatest { result ->
                    when (result) {
                        is Result.Loading -> topRatedMoviesState.emit(
                            MoviesScreenState(
                                isLoading = true
                            )
                        )
                        is Result.Error -> {
                            topRatedMoviesState.emit(
                                topRatedMoviesState.value.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    error = Error(true, result.message)
                                )
                            )
                        }
                        is Result.Success -> {
                            topRatedMoviesState.emit(
                                topRatedMoviesState.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    movies = result.data
                                )
                            )
                        }
                    }
                }
        }
    }
}
