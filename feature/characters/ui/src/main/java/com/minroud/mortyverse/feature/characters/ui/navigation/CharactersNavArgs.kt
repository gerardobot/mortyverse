package com.minroud.mortyverse.feature.characters.ui.navigation

import androidx.navigation.NavType
import com.minroud.mortyverse.ui.navigation.NavArg

object CharactersNavArgs {
    val CharacterId = NavArg(key = "character-id", navType = NavType.StringType)
}
