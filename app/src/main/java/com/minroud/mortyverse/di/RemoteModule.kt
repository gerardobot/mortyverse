package com.minroud.mortyverse.di

import com.minroud.mortyverse.data.sources.remote.RickAndMortyRemoteDataSource
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.ServiceInstanceCreator
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.RickAndMortyRetrofitRemoteDataSource
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.createRickAndMortyApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteModule = module {
    single { ServiceInstanceCreator(androidContext()) }

    single { createRickAndMortyApiServiceInstance(get()) }
    single<RickAndMortyRemoteDataSource> { RickAndMortyRetrofitRemoteDataSource(get()) }
}
