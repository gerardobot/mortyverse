package com.minroud.mortyverse.di

import com.minroud.mortyverse.infra.remote.di.remoteModule
import com.minroud.mortyverse.infra.ui.loaders.ImageLoader
import com.minroud.mortyverse.infra.ui.loaders.coil.CoilImageLoader
import org.koin.dsl.module

val infraModule = module {
    includes(remoteModule)
    factory<ImageLoader> { CoilImageLoader() }
}
