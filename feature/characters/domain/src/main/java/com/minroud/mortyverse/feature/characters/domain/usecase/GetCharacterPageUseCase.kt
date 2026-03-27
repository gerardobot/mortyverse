package com.minroud.mortyverse.feature.characters.domain.usecase

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.usecase.BaseUseCase
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.model.Page
import com.minroud.mortyverse.feature.characters.domain.repository.CharacterRepository

class GetCharacterPageUseCase(
    private val characterRepository: CharacterRepository,
) : BaseUseCase<GetCharacterPageUseCase.Params, Page<MortyverseCharacter>>() {
    override suspend fun call(params: Params): DomainResult<Page<MortyverseCharacter>> =
        characterRepository.getCharacterPage(params.pageNumber)

    data class Params(
        val pageNumber: Int,
    )
}
