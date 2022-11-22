package com.vendhan.kmmmovies.android.ui.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.vendhan.kmmmovies.viewmodel.movies.MoviesScreenSideEffects
import com.vendhan.kmmmovies.viewmodel.movies.MoviesViewModel
import org.koin.androidx.compose.get

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel = get(),
    onClick: (Int) -> Unit
) {
    LaunchedEffect(key1 = 1) {
        moviesViewModel.onIntent(MoviesScreenSideEffects.GetNowPlayingMovies)
        moviesViewModel.onIntent(MoviesScreenSideEffects.GetUpcomingMovies)
        moviesViewModel.onIntent(MoviesScreenSideEffects.GetTopRatedMovies)
    }

    val nowPlayingMoviesState by moviesViewModel.nowPlayingMoviesState.collectAsState()
    val upcomingMoviesState by moviesViewModel.upcomingMoviesState.collectAsState()
    val topRatedMoviesState by moviesViewModel.topRatedMoviesState.collectAsState()

    Scaffold {
        when {
            nowPlayingMoviesState.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            nowPlayingMoviesState.error.isError -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = nowPlayingMoviesState.error.errorMessage,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            nowPlayingMoviesState.isSuccess -> {
                LazyColumn(
                    horizontalAlignment = Alignment.Start,
                    content = {
                        item {
                            NowPlayingMoviesContent(
                                title = "Now Playing Movies",
                                movies = nowPlayingMoviesState.movies,
                                onClick = onClick
                            )
                        }
                        if (upcomingMoviesState.isSuccess) {
                            item {
                                UpcomingMoviesContent(
                                    title = "Upcoming Movies",
                                    movies = upcomingMoviesState.movies,
                                    onClick = onClick
                                )
                            }
                        }
                        if (topRatedMoviesState.isSuccess) {
                            item {
                                TopRatedMoviesContent(
                                    title = "Top Rated Movies",
                                    movies = topRatedMoviesState.movies,
                                    onClick = onClick
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

/*@Composable
fun MoviesCard(movie: MoviesResult) {
    Text(
        text = movie.title,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        fontSize = MaterialTheme.typography.h6.fontSize
    )
    Text(
        text = movie.overview,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 24.dp
            ),
        fontSize = MaterialTheme.typography.caption.fontSize
    )
}*/
