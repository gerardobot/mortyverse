package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterApi(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "species") val species: String,
    @Json(name = "type") val type: String,
    @Json(name = "status") val status: String,
    @Json(name = "image") val image: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "origin") val origin: OriginApi,
    @Json(name = "location") val location: LocationApi,
    @Json(name = "episode") val episodes: List<String>
)

fun CharacterApi.toMortyverseCharacter() = MortyverseCharacter(
    id = id.toString(),
    name = name,
    species = species,
    type = type,
    status = status,
    image = image
)

fun CharacterApi.toMortyverseCharacterDetail() = MortyverseCharacterDetail(
    mortyverseCharacter = this.toMortyverseCharacter(),
    gender = gender,
    origin = origin.name,
    location = location.name,
    episodes = episodes
)
