package com.minroud.mortyverse.feature.characters.infra.remote.retrofit

import com.minroud.mortyverse.feature.characters.infra.remote.api.CharacterApi
import com.minroud.mortyverse.feature.characters.infra.remote.api.CharacterResponseApi
import com.minroud.mortyverse.infra.remote.rickandmorty.RickAndMortyEndpoint
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitCharactersApiService {
    @GET(RickAndMortyEndpoint.Character.page)
    suspend fun getCharacterPage(
        @Query(RickAndMortyEndpoint.Character.Query.page)
        pageNumber: Int,
    ): CharacterResponseApi

    @GET(RickAndMortyEndpoint.Character.detail)
    suspend fun getCharacterDetail(
        @Path(RickAndMortyEndpoint.Character.Param.detail)
        id: String,
    ): CharacterApi
}
