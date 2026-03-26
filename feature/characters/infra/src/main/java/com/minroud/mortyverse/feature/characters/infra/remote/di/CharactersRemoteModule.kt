package com.minroud.mortyverse.feature.characters.infra.remote.di
import com.minroud.mortyverse.feature.characters.data.remote.CharacterRemoteDataSource
import com.minroud.mortyverse.feature.characters.infra.remote.retrofit.RetrofitCharactersApiService
import com.minroud.mortyverse.feature.characters.infra.remote.retrofit.RetrofitCharactersRemoteDataSource
import com.minroud.mortyverse.infra.remote.retrofit.NetworkQualifiers
import com.minroud.mortyverse.infra.remote.retrofit.createService
import org.koin.core.module.Module
import retrofit2.Retrofit

fun Module.charactersRemote() {
    single<RetrofitCharactersApiService> {
        get<Retrofit>(NetworkQualifiers.RickAndMorty).createService()
    }

    single<CharacterRemoteDataSource> {
        RetrofitCharactersRemoteDataSource(get())
    }
}
