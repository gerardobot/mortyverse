package com.minroud.mortyverse.ui.navigation

import androidx.navigation.NavHostController

interface Navigator {
    fun navigate(route: String)

    fun maybeNavigate(route: String)

    fun back()
}

class NavControllerNavigator(
    private val navController: NavHostController,
) : Navigator {
    override fun navigate(route: String) {
        navController.navigate(route)
    }

    override fun maybeNavigate(route: String) {
        if (navController.currentBackStackEntry?.destination?.route != route) {
            navController.navigate(route)
        }
    }

    override fun back() {
        navController.popBackStack()
    }
}
