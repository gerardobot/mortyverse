package com.minroud.mortyverse.feature.characters.data.repository

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.feature.characters.data.remote.CharacterRemoteDataSource
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.model.Page
import com.minroud.mortyverse.feature.characters.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {
    override suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>> =
        characterRemoteDataSource.getCharacters()

    override suspend fun getCharacterPage(pageNumber: Int): DomainResult<Page<MortyverseCharacter>> =
        characterRemoteDataSource.getCharacterPage(pageNumber)

    override suspend fun getCharacterDetail(characterId: String) =
        characterRemoteDataSource.getCharacterDetail(characterId)
}
