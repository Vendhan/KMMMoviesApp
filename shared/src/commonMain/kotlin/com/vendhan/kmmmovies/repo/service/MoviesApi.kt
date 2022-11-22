package com.vendhan.kmmmovies.repo.service

import com.vendhan.kmmmovies.repo.model.MoviesResultWrapper
import com.vendhan.kmmmovies.util.Result

interface MoviesApi {
    suspend fun getNowPlayingMovies(): Result<MoviesResultWrapper>

    suspend fun getUpcomingMovies(): Result<MoviesResultWrapper>

    suspend fun getTopRatedMovies(): Result<MoviesResultWrapper>
}
