package com.minroud.mortyverse

import android.app.Application
import com.minroud.mortyverse.di.infraModule
import com.minroud.mortyverse.di.repositoryModule
import com.minroud.mortyverse.di.useCaseModule
import com.minroud.mortyverse.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MortyverseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MortyverseApplication)
            modules(
                infraModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
