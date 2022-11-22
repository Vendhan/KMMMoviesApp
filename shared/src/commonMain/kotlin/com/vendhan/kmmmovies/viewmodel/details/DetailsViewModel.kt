package com.vendhan.kmmmovies.viewmodel.details

import com.vendhan.kmmmovies.repo.MoviesRepository
import com.vendhan.kmmmovies.util.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var movieState = MutableStateFlow(DetailsScreenState())

    fun onIntent(intent: DetailsScreenSideEffects) {
        when (intent) {
            is DetailsScreenSideEffects.GetMovieDetails -> getMovieDetails(id = intent.id)
        }
    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            moviesRepository
                .getMovie(id = id)
                .collectLatest { result ->
                    when (result) {
                        is Result.Loading -> movieState.update {
                            DetailsScreenState(
                                isLoading = true
                            )
                        }
                        is Result.Error ->
                            movieState.update {
                                it.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    error = Error(true, result.message)
                                )
                            }
                        is Result.Success ->
                            movieState.update {
                                it.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    movies = result.data
                                )
                            }
                    }
                }
        }
    }
}
