package com.vendhan.kmmmovies.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vendhan.kmmmovies.android.ui.navigation.MovieNavigationHost
import com.vendhan.kmmmovies.android.ui.theme.KMMMoviesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KMMMoviesTheme {
                MovieNavigationHost()
            }
        }
    }
}
