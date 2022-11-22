package com.vendhan.kmmmovies.di

import com.vendhan.kmmmovies.repo.MoviesRepository
import com.vendhan.kmmmovies.repo.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            get(),
            get(),
            Dispatchers.Default
        )
    }
}
