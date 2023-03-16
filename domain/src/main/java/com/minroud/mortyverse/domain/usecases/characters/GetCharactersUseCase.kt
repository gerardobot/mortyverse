package com.minroud.mortyverse.domain.usecases.characters

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.repositories.CharacterRepository
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.usecases.BaseUseCase

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Unit, List<MortyverseCharacter>>() {
    override suspend fun call(params: Unit): DomainResult<List<MortyverseCharacter>> =
        characterRepository.getCharacters()
}
