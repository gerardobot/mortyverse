package com.minroud.mortyverse.feature.characters.infra.remote.retrofit

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.feature.characters.data.remote.CharacterRemoteDataSource
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacterDetail
import com.minroud.mortyverse.feature.characters.domain.model.Page
import com.minroud.mortyverse.feature.characters.infra.remote.api.toCharacterPage
import com.minroud.mortyverse.feature.characters.infra.remote.api.toDomainError
import com.minroud.mortyverse.feature.characters.infra.remote.api.toMortyverseCharacterDetail

class RetrofitCharactersRemoteDataSource(
    private val api: RetrofitCharactersApiService,
) : CharacterRemoteDataSource {
    override suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>> =
        requestCatching {
            api.getCharacterPage(1).toCharacterPage().items
        }

    override suspend fun getCharacterPage(pageNumber: Int): DomainResult<Page<MortyverseCharacter>> =
        requestCatching {
            api.getCharacterPage(pageNumber).toCharacterPage()
        }

    override suspend fun getCharacterDetail(characterId: String): DomainResult<MortyverseCharacterDetail> =
        requestCatching {
            api.getCharacterDetail(characterId).toMortyverseCharacterDetail()
        }

    private suspend inline fun <T> requestCatching(block: suspend () -> T): DomainResult<T> =
        try {
            DomainResult.Success(block())
        } catch (e: Exception) {
            DomainResult.Error(e.toDomainError())
        }
}
