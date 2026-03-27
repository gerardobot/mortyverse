package com.minroud.mortyverse.feature.characters.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.minroud.mortyverse.feature.characters.ui.screens.detail.CharacterDetailScreen
import com.minroud.mortyverse.feature.characters.ui.screens.list.CharacterListScreen
import com.minroud.mortyverse.ui.navigation.Navigator
import com.minroud.mortyverse.ui.topbar.TopBarButton

fun NavGraphBuilder.charactersGraph(
    topBarButton: TopBarButton,
    navigator: Navigator,
) {
    composable(CharactersRoutes.list) {
        CharacterListScreen(
            topBarButton = topBarButton,
            onCharacterSelected = { navigator.navigate(CharactersRoutes.detail(it)) },
        )
    }

    composable(
        route = CharactersRoutes.detail,
        arguments = listOf(
            CharactersNavArgs.CharacterId.toNavArgument(),
        ),
    ) {
        CharacterDetailScreen(
            topBarButton = TopBarButton.Back { navigator.back() },
        )
    }
}
