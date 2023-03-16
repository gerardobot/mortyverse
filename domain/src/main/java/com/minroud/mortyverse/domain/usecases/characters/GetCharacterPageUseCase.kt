package com.minroud.mortyverse.domain.usecases.characters

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.pagination.Page
import com.minroud.mortyverse.domain.repositories.CharacterRepository
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.usecases.BaseUseCase

class GetCharacterPageUseCase(
    private val characterRepository: CharacterRepository
) : BaseUseCase<GetCharacterPageUseCase.Params, Page<MortyverseCharacter>>() {
    override suspend fun call(params: Params): DomainResult<Page<MortyverseCharacter>> =
        characterRepository.getCharacterPage(params.pageNumber)

    data class Params(val pageNumber: Int)
}
