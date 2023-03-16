package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty

import com.minroud.mortyverse.data.sources.remote.RickAndMortyRemoteDataSource
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.minroud.mortyverse.domain.entities.pagination.Page
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.Endpoints
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api.toCharacterPage
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api.toMortyverseCharacterDetail
import com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api.toMortyverseCharacterList

class RickAndMortyRemoteDataSourceRetrofit(
    private val rickAndMortyApiService: RickAndMortyApiService
) : RickAndMortyRemoteDataSource {
    override suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>> =
        requestCatching {
            rickAndMortyApiService.getCharacters().toMortyverseCharacterList()
        }

    override suspend fun getCharacterPage(
        pageNumber: Int
    ): DomainResult<Page<MortyverseCharacter>> = requestCatching {
        rickAndMortyApiService.getCharacterPage(pageNumber).toCharacterPage()
    }

    override suspend fun getCharacterDetail(
        characterId: String
    ): DomainResult<MortyverseCharacterDetail> = requestCatching {
        rickAndMortyApiService.getCharacterDetail(
            "${Endpoints.RickAndMorty.character}/$characterId"
        ).toMortyverseCharacterDetail()
    }

    private inline fun <T> requestCatching(
        block: () -> T
    ): DomainResult<T> = try {
        DomainResult.Success(block.invoke())
    } catch (requestException: Exception) {
        DomainResult.Error(requestException.toDomainError())
    }
}
