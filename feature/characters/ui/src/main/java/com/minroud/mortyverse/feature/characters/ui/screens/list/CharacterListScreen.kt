package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.minroud.mortyverse.domain.error.DomainError
import com.minroud.mortyverse.feature.characters.ui.screens.list.paging.CharactersPagingException
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import com.minroud.mortyverse.ui.topbar.TopBarButton
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
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

    val refreshState = characters.loadState.refresh
    val refreshError = (refreshState as? LoadState.Error)
        ?.error
        .toDomainErrorOrUnknown()

    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState, characters) {
        snapshotFlow { lazyListState.isNearEnd(3) }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                if (characters.loadState.append is LoadState.Error) characters.retry()
            }
    }

    CharacterList(
        characters = characters,
        topBarButton = topBarButton,
        onCharacterSelected = onCharacterSelected,
        imageLoader = imageLoader,
        lazyListState = lazyListState,
        isRefreshing = refreshState is LoadState.Loading,
        refreshError = refreshError,
    )
}

private fun Throwable?.toDomainErrorOrUnknown(): DomainError? =
    when (this) {
        null -> null
        is CharactersPagingException -> domainError
        else -> DomainError.Unknown(this)
    }
