package com.vendhan.kmmmovies.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vendhan.kmmmovies.viewmodel.details.DetailsViewModel
import com.vendhan.kmmmovies.viewmodel.movies.MoviesViewModel
import com.vendhan.showtime.db.MoviesDB
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Darwin.create()
    }
    // single or factory can be used to get a view-model object for swiftui
    single {
        MoviesViewModel(get())
    }
    single {
        DetailsViewModel(get())
    }
    single<SqlDriver> {
        NativeSqliteDriver(MoviesDB.Schema, "MoviesDB")
    }
}

/**
 * viewmodels object implements koin component thus its able to get any
 * dependency that is listed in platform module we can call getHomeviewmodel()
 * in swift ui to get an object of HomeViewModel
 */
object ViewModels : KoinComponent {
    fun getMoviesViewModel() = get<MoviesViewModel>()

    fun getDetailsViewModel() = get<DetailsViewModel>()
}
