package com.vendhan.kmmmovies.repo

import com.vendhan.kmmmovies.repo.model.MoviesResult
import com.vendhan.kmmmovies.util.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchNowPlayingMovies(): Flow<Result<List<MoviesResult>>>

    suspend fun fetchUpcomingMovies(): Flow<Result<List<MoviesResult>>>

    suspend fun fetchTopRatedMovies(): Flow<Result<List<MoviesResult>>>

    suspend fun getSimilarMovies()

    suspend fun getMovie(id: Int): Flow<Result<MoviesResult>>
}
