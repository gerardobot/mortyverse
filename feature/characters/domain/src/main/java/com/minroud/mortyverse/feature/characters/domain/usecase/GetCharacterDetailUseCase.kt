package com.minroud.mortyverse.feature.characters.domain.usecase

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.usecase.BaseUseCase
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacterDetail
import com.minroud.mortyverse.feature.characters.domain.repository.CharacterRepository

class GetCharacterDetailUseCase(
    private val characterRepository: CharacterRepository,
) : BaseUseCase<GetCharacterDetailUseCase.Params, MortyverseCharacterDetail>() {
    override suspend fun call(params: Params): DomainResult<MortyverseCharacterDetail> =
        characterRepository.getCharacterDetail(params.characterId)

    data class Params(
        val characterId: String,
    )
}
