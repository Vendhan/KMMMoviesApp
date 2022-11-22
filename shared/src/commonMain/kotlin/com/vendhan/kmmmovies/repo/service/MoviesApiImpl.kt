package com.vendhan.kmmmovies.repo.service

import com.vendhan.kmmmovies.repo.model.MoviesResultWrapper
import com.vendhan.kmmmovies.util.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class MoviesApiImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : MoviesApi {

    private val apikey = "5082488353dabdb7a6b596c5e6ad7bab"

    override suspend fun getNowPlayingMovies(): Result<MoviesResultWrapper> {
        return try {
            Result.Success(
                httpClient.get("$baseUrl/${EndPoints.NOW_PLAYING_MOVIES}") {
                    parameter("api_key", apikey)
                    parameter("language", "en-US")
                    parameter("page", 1)
                    parameter("region", "IN")
                }.body()
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Result.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Result.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Result.Error(e.response.status.description)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getUpcomingMovies(): Result<MoviesResultWrapper> {
        return try {
            Result.Success(
                httpClient.get("$baseUrl/${EndPoints.UPCOMING_MOVIES}") {
                    parameter("api_key", apikey)
                    parameter("language", "en-US")
                    parameter("page", 1)
                    parameter("region", "IN")
                }.body()
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Result.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Result.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Result.Error(e.response.status.description)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getTopRatedMovies(): Result<MoviesResultWrapper> {
        return try {
            Result.Success(
                httpClient.get("$baseUrl/${EndPoints.TOP_RATED_MOVIES}") {
                    parameter("api_key", apikey)
                    parameter("language", "en-US")
                    parameter("page", 1)
                    parameter("region", "IN")
                }.body()
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Result.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Result.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Result.Error(e.response.status.description)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Something went wrong")
        }
    }
}
