package com.minroud.mortyverse.feature.characters.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import com.minroud.mortyverse.ui.topbar.TopBarButton
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
internal fun CharacterDetailScreen(
    topBarButton: TopBarButton,
    viewModel: CharacterDetailViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val imageLoader: ImageLoader = koinInject()

    CharacterDetail(
        topBarButton = topBarButton,
        characterDetail = state.characterDetail,
        imageLoader = imageLoader,
    )
}
