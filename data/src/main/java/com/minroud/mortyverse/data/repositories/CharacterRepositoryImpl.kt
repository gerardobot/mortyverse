package com.minroud.mortyverse.data.repositories

import com.minroud.mortyverse.data.sources.remote.RickAndMortyRemoteDataSource
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.pagination.Page
import com.minroud.mortyverse.domain.repositories.CharacterRepository
import com.minroud.mortyverse.domain.result.DomainResult

class CharacterRepositoryImpl(
    private val rickAndMortyRemoteDataSource: RickAndMortyRemoteDataSource
) : CharacterRepository {
    override suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>> =
        rickAndMortyRemoteDataSource.getCharacters()

    override suspend fun getCharacterPage(
        pageNumber: Int
    ): DomainResult<Page<MortyverseCharacter>> =
        rickAndMortyRemoteDataSource.getCharacterPage(pageNumber)

    override suspend fun getCharacterDetail(characterId: String) =
        rickAndMortyRemoteDataSource.getCharacterDetail(characterId)
}
