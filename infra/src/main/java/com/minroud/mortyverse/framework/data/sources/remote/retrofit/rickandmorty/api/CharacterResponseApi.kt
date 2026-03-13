package com.minroud.mortyverse.infra.data.sources.remote.retrofit.rickandmorty.api

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.pagination.Page
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val PAGE_NUMBER_DELIMITER = "="

@Serializable
data class CharacterResponseApi(
    @SerialName("info") val paginationInfo: InfoApi,
    @SerialName("results") val characters: List<CharacterApi>
)

fun CharacterResponseApi.toMortyverseCharacterList() = characters.map { it.toMortyverseCharacter() }

fun CharacterResponseApi.toCharacterPage(): Page<MortyverseCharacter> = Page(
    previousPage = paginationInfo.previousPage?.substringAfter(PAGE_NUMBER_DELIMITER)
        ?.toInt(),
    nextPage = paginationInfo.nextPage?.substringAfter(PAGE_NUMBER_DELIMITER)?.toInt(),
    items = this.toMortyverseCharacterList()
)
