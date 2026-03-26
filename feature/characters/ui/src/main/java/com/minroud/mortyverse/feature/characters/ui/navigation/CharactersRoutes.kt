package com.minroud.mortyverse.feature.characters.ui.navigation

import com.minroud.mortyverse.ui.navigation.buildRoute

object CharactersRoutes {
    private const val invalidCharacterId = "90000"
    const val list = "character-list"
    val detail = "character-detail/{${CharactersNavArgs.CharacterId.key}}"
    val invalidCharacter = detail(invalidCharacterId)

    fun detail(id: String) =
        buildRoute(
            detail,
            CharactersNavArgs.CharacterId to id,
        )
}
