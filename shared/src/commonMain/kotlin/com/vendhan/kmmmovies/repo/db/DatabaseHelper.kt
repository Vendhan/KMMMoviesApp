package com.vendhan.kmmmovies.repo.db

import com.vendhan.kmmmovies.database.Movies
import com.vendhan.kmmmovies.repo.model.MoviesResult
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    fun selectAllMovies(): Flow<List<Movies>>

    suspend fun insertMovies(movies: List<MoviesResult>)

    suspend fun insertNowPlayingMovies(moviesIDs: List<Long>)

    suspend fun insertUpcomingMovies(moviesIDs: List<Long>)

    suspend fun insertTopRatedMovies(moviesIDs: List<Long>)

    fun selectAllNowPlayingMovies(): Flow<List<Movies>>

    fun selectAllUpcomingMovies(): Flow<List<Movies>>

    fun selectAllTopRatedMovies(): Flow<List<Movies>>

    fun selectMovie(id: Long): Flow<Movies>

    suspend fun deleteAll()
}
