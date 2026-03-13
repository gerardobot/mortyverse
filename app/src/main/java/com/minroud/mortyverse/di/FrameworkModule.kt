package com.minroud.mortyverse.di

import com.minroud.mortyverse.infra.ui.loaders.ImageLoader
import com.minroud.mortyverse.infra.ui.loaders.coil.ImageLoaderCoil
import org.koin.dsl.module

val infraModule = module {
    factory<ImageLoader> { ImageLoaderCoil() }
}
