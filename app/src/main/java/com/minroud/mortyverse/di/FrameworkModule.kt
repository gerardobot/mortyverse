package com.minroud.mortyverse.di

import com.minroud.mortyverse.framework.ui.loaders.ImageLoader
import com.minroud.mortyverse.framework.ui.loaders.coil.ImageLoaderCoil
import org.koin.dsl.module

val frameworkModule = module {
    factory<ImageLoader> { ImageLoaderCoil() }
}
