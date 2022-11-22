package com.vendhan.kmmmovies.di

import com.vendhan.kmmmovies.repo.service.MoviesApi
import com.vendhan.kmmmovies.repo.service.MoviesApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun getNetworkModule(
    enableNetworkLogs: Boolean,
    baseUrl: String
) = module {
    single<MoviesApi> {
        MoviesApiImpl(
            get(),
            baseUrl
        )
    }

    single { createJson() }

    single {
        createHttpClient(
            get(),
            get(),
            enableNetworkLogs = enableNetworkLogs
        )
    }
}

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) = HttpClient(httpClientEngine) {
    install(HttpTimeout) {
        val timeout = 30 * 1000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}

fun createJson() = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}
