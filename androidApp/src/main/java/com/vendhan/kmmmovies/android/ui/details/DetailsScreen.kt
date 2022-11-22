package com.vendhan.kmmmovies.android.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vendhan.kmmmovies.viewmodel.details.DetailsScreenSideEffects
import com.vendhan.kmmmovies.viewmodel.details.DetailsViewModel
import org.koin.androidx.compose.get

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = get(),
    movieID: Int
) {
    LaunchedEffect(key1 = 1) {
        detailsViewModel.onIntent(DetailsScreenSideEffects.GetMovieDetails(id = movieID))
    }
    val movieDetailState by detailsViewModel.movieState.collectAsState()

    Scaffold(
        topBar = {
            Text(
                text = movieDetailState.movies?.title ?: "Movie Detail",
                modifier = Modifier.padding(6.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    ) {
        when {
            movieDetailState.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            movieDetailState.error.isError -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = movieDetailState.error.errorMessage,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            movieDetailState.isSuccess -> {
                movieDetailState.movies?.let {
                    DetailsScreenContent(it)
                }
            }
        }
    }
}
