package com.minroud.mortyverse.infra.remote.rickandmorty.api

import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterApi(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("species") val species: String,
    @SerialName("type") val type: String,
    @SerialName("status") val status: String,
    @SerialName("image") val image: String,
    @SerialName("gender") val gender: String,
    @SerialName("origin") val origin: OriginApi,
    @SerialName("location") val location: LocationApi,
    @SerialName("episode") val episodes: List<String>
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
