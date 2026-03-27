package com.minroud.mortyverse.infra.ui.di

import com.minroud.mortyverse.infra.ui.imageloader.coil.CoilImageLoader
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import org.koin.core.module.Module

fun Module.infraUi() {
    factory<ImageLoader> { CoilImageLoader() }
}
