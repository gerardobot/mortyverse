package com.minroud.mortyverse.domain.entities.character

data class MortyverseCharacterDetail(
    val mortyverseCharacter: MortyverseCharacter,
    val gender: String,
    val origin: String,
    val location: String,
    val episodes: List<String>
)
