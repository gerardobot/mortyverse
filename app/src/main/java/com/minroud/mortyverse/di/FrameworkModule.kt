package com.minroud.mortyverse.di

import com.minroud.mortyverse.framework.ui.image.AsyncImage
import com.minroud.mortyverse.framework.ui.image.Gif
import com.minroud.mortyverse.framework.ui.image.coil.CoilAsyncImage
import com.minroud.mortyverse.framework.ui.image.coil.CoilGif
import org.koin.dsl.module

val frameworkModule = module {
    factory<AsyncImage> { CoilAsyncImage() }
    factory<Gif> { CoilGif() }
}
