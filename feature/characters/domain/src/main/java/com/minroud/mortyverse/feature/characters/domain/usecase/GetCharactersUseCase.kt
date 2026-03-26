package com.minroud.mortyverse.feature.characters.domain.usecase

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.usecase.BaseUseCase
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository,
) : BaseUseCase<Unit, List<MortyverseCharacter>>() {
    override suspend fun call(params: Unit): DomainResult<List<MortyverseCharacter>> =
        characterRepository.getCharacters()
}
