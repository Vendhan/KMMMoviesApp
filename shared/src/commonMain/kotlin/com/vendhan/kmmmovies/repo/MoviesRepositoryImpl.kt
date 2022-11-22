package com.vendhan.kmmmovies.repo

import com.vendhan.kmmmovies.repo.db.DatabaseHelper
import com.vendhan.kmmmovies.repo.model.MoviesResult
import com.vendhan.kmmmovies.repo.model.toModel
import com.vendhan.kmmmovies.repo.service.MoviesApi
import com.vendhan.kmmmovies.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val dbHelper: DatabaseHelper,
    private val moviesApi: MoviesApi,
    private val ioDispatcher: CoroutineDispatcher
) : MoviesRepository {

    override suspend fun fetchNowPlayingMovies(): Flow<Result<List<MoviesResult>>> {
        return withContext(ioDispatcher) {
            flow {
                emit(Result.Loading)
                try {
                    dbHelper
                        .selectAllNowPlayingMovies()
                        .collect {
                            if (it.isNotEmpty()) {
                                val list = it.map { movie ->
                                    movie.toModel()
                                }
                                emit(Result.Success(data = list))
                            } else {
                                val apiResult = moviesApi
                                    .getNowPlayingMovies()
                                if (apiResult is Result.Success) {
                                    dbHelper.insertMovies(apiResult.data.results)
                                    dbHelper.insertNowPlayingMovies(apiResult.data.results.map { it.id.toLong() })
                                    dbHelper
                                        .selectAllNowPlayingMovies()
                                        .collect { movies ->
                                            val list = movies.map { movie ->
                                                movie.toModel()
                                            }
                                            emit(Result.Success(data = list))
                                        }
                                } else {
                                    emit(Result.Error("Some error"))
                                }
                            }
                        }
                } catch (e: Exception) {
                    emit(Result.Error("Some Error"))
                }
            }
        }
    }

    override suspend fun fetchUpcomingMovies(): Flow<Result<List<MoviesResult>>> {
        return withContext(ioDispatcher) {
            flow {
                emit(Result.Loading)
                try {
                    dbHelper
                        .selectAllUpcomingMovies()
                        .collect {
                            if (it.isNotEmpty()) {
                                val list = it.map { movie ->
                                    movie.toModel()
                                }
                                emit(Result.Success(data = list))
                            } else {
                                val apiResult = moviesApi
                                    .getUpcomingMovies()
                                if (apiResult is Result.Success) {
                                    dbHelper.insertMovies(apiResult.data.results)
                                    dbHelper.insertUpcomingMovies(apiResult.data.results.map { it.id.toLong() })
                                    dbHelper
                                        .selectAllUpcomingMovies()
                                        .collect { movies ->
                                            val list = movies.map { movie ->
                                                movie.toModel()
                                            }
                                            emit(Result.Success(data = list))
                                        }
                                } else {
                                    emit(Result.Error("Some error"))
                                }
                            }
                        }
                } catch (e: Exception) {
                    emit(Result.Error("Some Error"))
                }
            }
        }
    }

    override suspend fun fetchTopRatedMovies(): Flow<Result<List<MoviesResult>>> {
        return withContext(ioDispatcher) {
            flow {
                emit(Result.Loading)
                try {
                    dbHelper
                        .selectAllTopRatedMovies()
                        .collect {
                            if (it.isNotEmpty()) {
                                val list = it.map { movie ->
                                    movie.toModel()
                                }
                                emit(Result.Success(data = list))
                            } else {
                                val apiResult = moviesApi
                                    .getTopRatedMovies()
                                if (apiResult is Result.Success) {
                                    dbHelper.insertMovies(apiResult.data.results)
                                    dbHelper.insertTopRatedMovies(apiResult.data.results.map { it.id.toLong() })
                                    dbHelper
                                        .selectAllTopRatedMovies()
                                        .collect { movies ->
                                            val list = movies.map { movie ->
                                                movie.toModel()
                                            }
                                            emit(Result.Success(data = list))
                                        }
                                } else {
                                    emit(Result.Error("Some error"))
                                }
                            }
                        }
                } catch (e: Exception) {
                    emit(Result.Error("Some Error"))
                }
            }
        }
    }

    override suspend fun getSimilarMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getMovie(id: Int): Flow<Result<MoviesResult>> {
        return withContext(ioDispatcher) {
            flow {
                try {
                    dbHelper
                        .selectMovie(
                            id = id.toLong()
                        )
                        .collect {
                            emit(Result.Success(data = it.toModel()))
                        }
                } catch (e: Exception) {
                    emit(Result.Error("Some Error"))
                }
            }
        }
    }
}
