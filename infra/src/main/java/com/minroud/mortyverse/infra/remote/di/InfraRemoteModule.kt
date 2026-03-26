package com.minroud.mortyverse.infra.remote.di

import com.minroud.mortyverse.infra.remote.okhttp.createOkHttpClient
import com.minroud.mortyverse.infra.remote.retrofit.NetworkQualifiers
import com.minroud.mortyverse.infra.remote.retrofit.createRetrofit
import com.minroud.mortyverse.infra.remote.rickandmorty.RickAndMortyEndpoint
import kotlinx.serialization.json.Json
import org.koin.core.module.Module

fun Module.infraRemote() {
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single { createOkHttpClient(get()) }

    single(NetworkQualifiers.RickAndMorty) {
        createRetrofit(
            RickAndMortyEndpoint.base,
            get(),
            get(),
        )
    }
}
