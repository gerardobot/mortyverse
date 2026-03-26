package com.minroud.mortyverse.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

data class NavArg<T>(
    val key: String,
    val navType: NavType<T>,
) {
    fun toNavArgument() = navArgument(key) { type = navType }
}
