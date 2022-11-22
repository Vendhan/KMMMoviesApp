package com.vendhan.kmmmovies.repo.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.vendhan.kmmmovies.database.Movies
import com.vendhan.kmmmovies.repo.model.MoviesResult
import com.vendhan.showtime.db.MoviesDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DatabaseHelperImpl(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) : DatabaseHelper {
    private val dbRef: MoviesDB = MoviesDB(sqlDriver)

    override fun selectAllMovies(): Flow<List<Movies>> =
        dbRef.moviesTableQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    override suspend fun insertMovies(movies: List<MoviesResult>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            movies.forEach { movie ->
                dbRef.moviesTableQueries.insertMovie(
                    id = movie.id.toLong(),
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.posterPath,
                    releaseDate = movie.releaseDate,
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount.toLong()
                )
            }
        }
    }

    override suspend fun insertNowPlayingMovies(moviesIDs: List<Long>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            moviesIDs.forEach { id ->
                dbRef.nowPlayingMoviesTableQueries.insertID(
                    movieID = id
                )
            }
        }
    }

    override suspend fun insertUpcomingMovies(moviesIDs: List<Long>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            moviesIDs.forEach { id ->
                dbRef.upcomingMoviesTableQueries.insertID(
                    movieID = id
                )
            }
        }
    }

    override suspend fun insertTopRatedMovies(moviesIDs: List<Long>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            moviesIDs.forEach { id ->
                dbRef.topRatedMoviesTableQueries.insertID(
                    movieID = id
                )
            }
        }
    }

    override fun selectAllNowPlayingMovies(): Flow<List<Movies>> {
        return dbRef.nowPlayingMoviesTableQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)
    }

    override fun selectAllUpcomingMovies(): Flow<List<Movies>> {
        return dbRef.upcomingMoviesTableQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)
    }

    override fun selectAllTopRatedMovies(): Flow<List<Movies>> {
        return dbRef.topRatedMoviesTableQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)
    }

    override fun selectMovie(id: Long): Flow<Movies> {
        return dbRef.moviesTableQueries
            .selectById(id)
            .asFlow()
            .mapToOne()
            .flowOn(backgroundDispatcher)
    }

    override suspend fun deleteAll() {
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.moviesTableQueries.deleteAll()
        }
    }
}
