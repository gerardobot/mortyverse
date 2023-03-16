package com.minroud.mortyverse.domain.usecases.characters

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.minroud.mortyverse.domain.repositories.CharacterRepository
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.usecases.BaseUseCase

class GetCharacterDetailUseCase(
    private val characterRepository: CharacterRepository
) : BaseUseCase<GetCharacterDetailUseCase.Params, MortyverseCharacterDetail>() {
    override suspend fun call(params: Params): DomainResult<MortyverseCharacterDetail> =
        characterRepository.getCharacterDetail(params.characterId)

    data class Params(val characterId: String)
}
