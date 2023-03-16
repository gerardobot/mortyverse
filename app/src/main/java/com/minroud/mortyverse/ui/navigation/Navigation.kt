package com.minroud.mortyverse.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.minroud.mortyverse.ui.MortyverseState
import com.minroud.mortyverse.ui.rememberMortyverseState
import com.minroud.mortyverse.ui.screens.character.detail.CharacterDetail
import com.minroud.mortyverse.ui.screens.character.list.CharacterList
import com.minroud.mortyverse.ui.screens.demo.error.NetworkErrorScreen
import com.minroud.mortyverse.ui.screens.demo.error.UnknownErrorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    mortyverseState: MortyverseState = rememberMortyverseState()
) {
    with(mortyverseState) {
        NavHost(
            navController = navController,
            startDestination = Route.CharacterList.name
        ) {
            composable(route = Route.CharacterList.name) {
                CharacterList(
                    topBarButton = TopBarButton.Menu { onMenuClick() },
                    drawerState = drawerState,
                    drawerOptions = drawerOptions(mortyverseState),
                    onCharacterSelected = {
                        navController.navigate(
                            Route.CharacterDetail.getRouteWithArgs(NavArg.CharacterId to it)
                        )
                    }
                )
            }

            composable(
                route = Route.CharacterDetail.name,
                arguments = listOf(
                    NavArg.CharacterId.toNavArgument()
                )
            ) {
                CharacterDetail(topBarButton = TopBarButton.Back { navController.popBackStack() })
            }

            composable(
                route = Route.NetworkError.name
            ) {
                NetworkErrorScreen(topBarButton = TopBarButton.Back { navController.popBackStack() })
            }

            composable(
                route = Route.UnknownError.name
            ) {
                UnknownErrorScreen(topBarButton = TopBarButton.Back { navController.popBackStack() })
            }
        }
    }
}

private fun drawerOptions(mortyverseState: MortyverseState) =
    with(mortyverseState) {
        listOf(
            DrawerOption.Home { onDrawerOptionClick(it) },
            DrawerOption.InvalidCharacterId { onDrawerOptionClick(it) },
            DrawerOption.NetworkError { onDrawerOptionClick(it) },
            DrawerOption.UnknownError { onDrawerOptionClick(it) }
        )
    }

sealed class NavArg(val key: String, private val navType: NavType<*>) {
    object CharacterId : NavArg("character-id", NavType.StringType)

    fun toNavArgument() = navArgument(key) { type = navType }
}
