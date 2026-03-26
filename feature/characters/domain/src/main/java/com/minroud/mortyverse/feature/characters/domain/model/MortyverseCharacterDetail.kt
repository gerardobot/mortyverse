package com.minroud.mortyverse.feature.characters.domain.model

data class MortyverseCharacterDetail(
    val mortyverseCharacter: MortyverseCharacter,
    val gender: String,
    val origin: String,
    val location: String,
    val episodes: List<String>,
)
