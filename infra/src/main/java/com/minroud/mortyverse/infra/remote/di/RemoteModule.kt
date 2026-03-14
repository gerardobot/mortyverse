package com.minroud.mortyverse.infra.remote.di

import com.minroud.mortyverse.data.sources.remote.RickAndMortyRemoteDataSource
import com.minroud.mortyverse.infra.remote.okhttp.createOkHttpClient
import com.minroud.mortyverse.infra.remote.retrofit.NetworkQualifiers
import com.minroud.mortyverse.infra.remote.retrofit.createRetrofit
import com.minroud.mortyverse.infra.remote.retrofit.createService
import com.minroud.mortyverse.infra.remote.rickandmorty.RickAndMortyApiService
import com.minroud.mortyverse.infra.remote.rickandmorty.RickAndMortyEndpoint
import com.minroud.mortyverse.infra.remote.rickandmorty.RickAndMortyRemoteDataSourceRetrofit
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module {
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
            get()
        )
    }

    single<RickAndMortyApiService> {
        get<Retrofit>(NetworkQualifiers.RickAndMorty).createService()
    }

    single<RickAndMortyRemoteDataSource> {
        RickAndMortyRemoteDataSourceRetrofit(get())
    }
}
