package com.minroud.mortyverse.navigation

import com.minroud.mortyverse.R
import com.minroud.mortyverse.feature.characters.ui.navigation.CharactersRoutes
import com.minroud.mortyverse.feature.demo.ui.navigation.DemoRoutes
import com.minroud.mortyverse.ui.navigation.drawer.DrawerItem

internal fun drawerItems(): List<DrawerItem> =
    listOf(
        DrawerItem(
            nameRes = R.string.drawer_option_home,
            route = CharactersRoutes.list,
        ),
        DrawerItem(
            nameRes = R.string.drawer_option_invalid_character_id,
            route = CharactersRoutes.invalidCharacter,
        ),
        DrawerItem(
            nameRes = R.string.drawer_option_network_error,
            route = DemoRoutes.networkError,
        ),
        DrawerItem(
            nameRes = R.string.drawer_option_unknown_error,
            route = DemoRoutes.unknownError,
        ),
    )
