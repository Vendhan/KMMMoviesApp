package com.vendhan.kmmmovies.di

import com.vendhan.kmmmovies.repo.db.DatabaseHelper
import com.vendhan.kmmmovies.repo.db.DatabaseHelperImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val databaseModule = module {
    single<DatabaseHelper> {
        DatabaseHelperImpl(
            get(),
            Dispatchers.Default
        )
    }
}
