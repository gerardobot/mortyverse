package com.minroud.mortyverse.di

import com.minroud.mortyverse.di.core.infra
import com.minroud.mortyverse.di.feature.characters
import org.koin.dsl.module

val appModule = module {
    infra()
    characters()
}
