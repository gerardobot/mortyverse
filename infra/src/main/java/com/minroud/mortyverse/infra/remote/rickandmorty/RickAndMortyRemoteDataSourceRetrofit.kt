package com.minroud.mortyverse.infra.remote.rickandmorty

import com.minroud.mortyverse.data.sources.remote.RickAndMortyRemoteDataSource
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.minroud.mortyverse.domain.entities.pagination.Page
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.infra.remote.rickandmorty.api.toCharacterPage
import com.minroud.mortyverse.infra.remote.rickandmorty.api.toMortyverseCharacterDetail

class RickAndMortyRemoteDataSourceRetrofit(
    private val api: RickAndMortyApiService
) : RickAndMortyRemoteDataSource {

    override suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>> =
        requestCatching {
            api.getCharacterPage(1).toCharacterPage().items
        }

    override suspend fun getCharacterPage(
        pageNumber: Int
    ): DomainResult<Page<MortyverseCharacter>> =
        requestCatching {
            api.getCharacterPage(pageNumber).toCharacterPage()
        }

    override suspend fun getCharacterDetail(
        characterId: String
    ): DomainResult<MortyverseCharacterDetail> =
        requestCatching {
            api.getCharacterDetail(characterId).toMortyverseCharacterDetail()
        }

    private suspend inline fun <T> requestCatching(
        block: suspend () -> T
    ): DomainResult<T> =
        try {
            DomainResult.Success(block())
        } catch (e: Exception) {
            DomainResult.Error(e.toDomainError())
        }
}
