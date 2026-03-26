package com.minroud.mortyverse.ui.navigation.drawer

import androidx.annotation.StringRes

data class DrawerItem(
    @StringRes val nameRes: Int,
    val route: String,
)
