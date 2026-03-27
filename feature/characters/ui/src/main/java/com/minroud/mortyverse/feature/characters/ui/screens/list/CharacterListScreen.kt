package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import com.minroud.mortyverse.ui.topbar.TopBarButton
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
internal fun CharacterListScreen(
    topBarButton: TopBarButton,
    onCharacterSelected: (String) -> Unit,
    viewModel: CharacterListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val imageLoader: ImageLoader = koinInject()

    CharacterList(
        state = state,
        topBarButton = topBarButton,
        onCharacterSelected = onCharacterSelected,
        onScrollEnd = viewModel::onScrollEnd,
        imageLoader = imageLoader,
    )
}
