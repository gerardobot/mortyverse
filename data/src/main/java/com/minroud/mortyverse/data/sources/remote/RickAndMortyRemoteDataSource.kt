package com.minroud.mortyverse.data.sources.remote

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.minroud.mortyverse.domain.entities.pagination.Page
import com.minroud.mortyverse.domain.result.DomainResult

interface RickAndMortyRemoteDataSource {
    suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>>
    suspend fun getCharacterPage(pageNumber: Int): DomainResult<Page<MortyverseCharacter>>
    suspend fun getCharacterDetail(characterId: String): DomainResult<MortyverseCharacterDetail>
}
