package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.pagination.Page
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val PAGE_NUMBER_DELIMITER = "="

@JsonClass(generateAdapter = true)
data class CharacterResponseApi(
    @Json(name = "info") val paginationInfo: InfoApi,
    @Json(name = "results") val characters: List<CharacterApi>
)

fun CharacterResponseApi.toMortyverseCharacterList() = characters.map { it.toMortyverseCharacter() }

fun CharacterResponseApi.toCharacterPage(): Page<MortyverseCharacter> = Page(
    previousPage = paginationInfo.previousPage?.substringAfter(PAGE_NUMBER_DELIMITER)
        ?.toInt(),
    nextPage = paginationInfo.nextPage?.substringAfter(PAGE_NUMBER_DELIMITER)?.toInt(),
    items = this.toMortyverseCharacterList()
)
