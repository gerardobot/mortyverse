package com.minroud.mortyverse.feature.demo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.minroud.mortyverse.feature.demo.ui.screens.errors.network.NetworkErrorScreen
import com.minroud.mortyverse.feature.demo.ui.screens.errors.unknown.UnknownErrorScreen
import com.minroud.mortyverse.ui.navigation.Navigator
import com.minroud.mortyverse.ui.topbar.TopBarButton

fun NavGraphBuilder.demoGraph(navigator: Navigator) {
    composable(route = DemoRoutes.networkError) {
        NetworkErrorScreen(topBarButton = TopBarButton.Back { navigator.back() })
    }

    composable(route = DemoRoutes.unknownError) {
        UnknownErrorScreen(topBarButton = TopBarButton.Back { navigator.back() })
    }
}
