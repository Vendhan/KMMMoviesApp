package com.vendhan.kmmmovies.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vendhan.kmmmovies.android.ui.details.DetailsScreen
import com.vendhan.kmmmovies.android.ui.movies.MoviesScreen
import org.koin.androidx.compose.get


@Composable
fun MovieNavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MoviesScreen
    ) {
        composable(
            route = Screen.MoviesScreen
        ) {
            MoviesScreen(
                moviesViewModel = get(),
                onClick = { movieID ->
                    navController.navigate(
                        route = Screen.MovieDetailsScreen + "?${Constants.MOVIE_ID}=$movieID"
                    )
                }
            )
        }
        composable(
            route = Screen.MovieDetailsScreen + "?${Constants.MOVIE_ID}={${Constants.MOVIE_ID}}",
            arguments = listOf(
                navArgument(name = Constants.MOVIE_ID) {
                    this.type = NavType.IntType
                    this.defaultValue = -1
                    this.nullable = false
                }
            )
        ) {
            val movieID = it.arguments?.getInt(Constants.MOVIE_ID) ?: -1
            DetailsScreen(
                movieID = movieID
            )
        }
    }
}
