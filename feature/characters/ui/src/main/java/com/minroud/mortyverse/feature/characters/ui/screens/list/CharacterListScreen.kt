package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
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
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val imageLoader: ImageLoader = koinInject()

    CharacterList(
        characters = characters,
        topBarButton = topBarButton,
        onCharacterSelected = onCharacterSelected,
        imageLoader = imageLoader,
    )
}
