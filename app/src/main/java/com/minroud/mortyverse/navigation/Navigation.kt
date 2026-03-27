package com.minroud.mortyverse.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.minroud.mortyverse.feature.characters.ui.navigation.CharactersRoutes
import com.minroud.mortyverse.feature.characters.ui.navigation.charactersGraph
import com.minroud.mortyverse.feature.demo.ui.navigation.demoGraph
import com.minroud.mortyverse.ui.navigation.NavControllerNavigator
import com.minroud.mortyverse.ui.navigation.drawer.DrawerContent
import com.minroud.mortyverse.ui.navigation.drawer.DrawerStateController
import com.minroud.mortyverse.ui.topbar.TopBarButton

@Composable
fun Navigation() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    val navigator = remember(navController) { NavControllerNavigator(navController) }
    val drawerController = remember(drawerState, coroutineScope) { DrawerStateController(drawerState, coroutineScope) }

    val drawerMenuItems = remember { drawerItems() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.primary,
        drawerContent = {
            DrawerContent(
                drawerItems = drawerMenuItems,
                onItemClick = {
                    drawerController.close()
                    navigator.maybeNavigate(it.route)
                },
            )
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = CharactersRoutes.list,
        ) {
            charactersGraph(
                topBarButton = TopBarButton.Menu { drawerController.open() },
                navigator = navigator,
            )

            demoGraph(navigator = navigator)
        }
    }
}
