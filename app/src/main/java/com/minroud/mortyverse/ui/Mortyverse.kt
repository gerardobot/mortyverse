package com.minroud.mortyverse.ui

import androidx.compose.runtime.Composable
import com.minroud.mortyverse.ui.navigation.Navigation
import com.minroud.mortyverse.ui.screens.MortyverseScreen

@Composable
fun Mortyverse() = MortyverseScreen {
    Navigation()
}
