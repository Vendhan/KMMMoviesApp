package com.vendhan.kmmmovies.android.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vendhan.kmmmovies.repo.model.MoviesResult

@Composable
fun NowPlayingMoviesContent(
    title: String,
    movies: List<MoviesResult>,
    onClick: (Int) -> Unit
) {
    val state: LazyListState = rememberLazyListState()
    MoviesHeader(
        title = title
    )
    LazyRow(
        state = state,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies, key = {
            it.id
        }, contentType = {
                it::class.java
            }) { item ->
            MoviesCard(
                movie = item,
                onClick = onClick
            )
        }
    }
}
