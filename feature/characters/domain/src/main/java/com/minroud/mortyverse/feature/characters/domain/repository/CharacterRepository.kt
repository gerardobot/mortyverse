package com.minroud.mortyverse.feature.characters.domain.repository

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacterDetail
import com.minroud.mortyverse.feature.characters.domain.model.Page

interface CharacterRepository {
    suspend fun getCharacters(): DomainResult<List<MortyverseCharacter>>

    suspend fun getCharacterPage(pageNumber: Int): DomainResult<Page<MortyverseCharacter>>

    suspend fun getCharacterDetail(characterId: String): DomainResult<MortyverseCharacterDetail>
}
