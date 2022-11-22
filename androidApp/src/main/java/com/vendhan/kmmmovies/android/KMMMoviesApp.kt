package com.vendhan.kmmmovies.android

import android.app.Application
import com.vendhan.kmmmovies.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class KMMMoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            baseUrl = BASE_URL,
            enableNetworkLogs = BuildConfig.DEBUG
        ) {
            androidContext(this@KMMMoviesApp)
            // androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.INFO)
            modules(
                listOf(
                    module {
                        /**
                         * android specific modules
                         */
                    }
                 )
            )
        }
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3"
    }
}
