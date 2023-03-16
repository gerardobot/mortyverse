package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty

import com.minroud.mortyverse.framework.data.sources.remote.retrofit.*
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api.CharacterApi
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api.CharacterResponseApi
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyApiService {
    @GET(Endpoints.RickAndMorty.character)
    suspend fun getCharacters(): CharacterResponseApi

    @GET(Endpoints.RickAndMorty.character)
    suspend fun getCharacterPage(
        @Query(Queries.RickAndMorty.characterPage) pageNumber: Int
    ): CharacterResponseApi

    @GET
    suspend fun getCharacterDetail(
        @Url characterUrl: String
    ): CharacterApi
}

fun createRickAndMortyApiServiceInstance(
    serviceInstanceCreator: ServiceInstanceCreator
): RickAndMortyApiService = serviceInstanceCreator.create(BaseUrls.rickAndMorty)
