package com.minroud.mortyverse.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.minroud.mortyverse.ui.navigation.DrawerOption
import com.minroud.mortyverse.ui.navigation.NavArg
import com.minroud.mortyverse.ui.navigation.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberMortyverseState(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MortyverseState = remember(drawerState, navController, coroutineScope) {
    MortyverseState(drawerState, navController, coroutineScope)
}

@OptIn(ExperimentalMaterial3Api::class)
class MortyverseState(
    val drawerState: DrawerState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
    fun onDrawerOptionClick(option: DrawerOption) {
        coroutineScope.launch { drawerState.close() }

        when (option) {
            is DrawerOption.Home -> { maybeNavigate(Route.CharacterList.name) }
            is DrawerOption.InvalidCharacterId -> {
                maybeNavigate(Route.CharacterDetail.getRouteWithArgs(NavArg.CharacterId to "9000"))
            }
            is DrawerOption.NetworkError -> { maybeNavigate(Route.NetworkError.name) }
            is DrawerOption.UnknownError -> { maybeNavigate(Route.UnknownError.name) }
        }
    }

    fun onMenuClick() {
        coroutineScope.launch { drawerState.open() }
    }

    private fun maybeNavigate(route: String) {
        if (navController.currentBackStackEntry?.destination?.route != route) {
            navController.navigate(route)
        }
    }
}
