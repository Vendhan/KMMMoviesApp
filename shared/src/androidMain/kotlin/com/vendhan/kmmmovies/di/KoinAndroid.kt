package com.vendhan.kmmmovies.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.vendhan.kmmmovies.viewmodel.details.DetailsViewModel
import com.vendhan.kmmmovies.viewmodel.movies.MoviesViewModel
import com.vendhan.showtime.db.MoviesDB
import io.ktor.client.engine.android.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Android.create()
    }
    viewModel {
        MoviesViewModel(
            get()
        )
    }
    viewModel {
        DetailsViewModel(
            get()
        )
    }
    single<SqlDriver> {
        AndroidSqliteDriver(
            MoviesDB.Schema,
            get(),
            "MoviesDB"
        )
    }
}
