package com.vendhan.kmmmovies.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    enableNetworkLogs: Boolean = false,
    baseUrl: String,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            repositoryModule,
            getNetworkModule(
                enableNetworkLogs = enableNetworkLogs,
                baseUrl = baseUrl
            ),
            databaseModule
        )
    }

// called by iOS etc
fun initKoin(baseUrl: String) = initKoin(enableNetworkLogs = true, baseUrl) {}
