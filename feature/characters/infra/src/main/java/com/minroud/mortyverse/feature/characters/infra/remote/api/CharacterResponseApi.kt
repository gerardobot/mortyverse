package com.minroud.mortyverse.feature.characters.infra.remote.api

import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.model.Page
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val PAGE_NUMBER_DELIMITER = "="

@Serializable
data class CharacterResponseApi(
    @SerialName("info") val paginationInfo: InfoApi,
    @SerialName("results") val characters: List<CharacterApi>,
)

fun CharacterResponseApi.toMortyverseCharacterList() = characters.map { it.toMortyverseCharacter() }

fun CharacterResponseApi.toCharacterPage(): Page<MortyverseCharacter> =
    Page(
        previousPage = paginationInfo.previousPage?.substringAfter(PAGE_NUMBER_DELIMITER)
            ?.toInt(),
        nextPage = paginationInfo.nextPage?.substringAfter(PAGE_NUMBER_DELIMITER)?.toInt(),
        items = this.toMortyverseCharacterList(),
    )
