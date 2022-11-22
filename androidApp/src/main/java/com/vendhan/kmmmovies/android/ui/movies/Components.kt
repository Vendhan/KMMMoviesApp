package com.vendhan.kmmmovies.android.ui.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoviesHeader(title: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = title,
        style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
        fontWeight = FontWeight.Bold
    )
}