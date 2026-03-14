package com.minroud.mortyverse.infra.remote.rickandmorty

import com.minroud.mortyverse.infra.remote.rickandmorty.api.CharacterApi
import com.minroud.mortyverse.infra.remote.rickandmorty.api.CharacterResponseApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET(RickAndMortyEndpoint.Character.page)
    suspend fun getCharacterPage(
        @Query(RickAndMortyEndpoint.Character.Query.page)
        pageNumber: Int
    ): CharacterResponseApi

    @GET(RickAndMortyEndpoint.Character.detail)
    suspend fun getCharacterDetail(
        @Path(RickAndMortyEndpoint.Character.Param.detail)
        id: String
    ): CharacterApi
}
