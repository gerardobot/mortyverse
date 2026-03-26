package com.minroud.mortyverse.infra.ui.loaders.di

import com.minroud.mortyverse.infra.ui.loaders.ImageLoader
import com.minroud.mortyverse.infra.ui.loaders.coil.CoilImageLoader
import org.koin.core.module.Module

fun Module.infraUi() {
    factory<ImageLoader> { CoilImageLoader() }
}
