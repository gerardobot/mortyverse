package com.minroud.mortyverse

import android.app.Application
import com.minroud.mortyverse.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MortyverseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MortyverseApplication)
            modules(
                frameworkModule,
                remoteModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
