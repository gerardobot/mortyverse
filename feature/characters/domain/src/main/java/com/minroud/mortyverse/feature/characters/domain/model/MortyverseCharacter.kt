package com.minroud.mortyverse.feature.characters.domain.model

data class MortyverseCharacter(
    val id: String,
    val name: String,
    val species: String,
    val type: String,
    val status: String,
    val image: String,
)
