package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.ui.R
import com.minroud.mortyverse.feature.characters.ui.screens.list.components.CharacterCard
import com.minroud.mortyverse.feature.characters.ui.screens.list.paging.CharactersPagingException
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import com.minroud.mortyverse.ui.animations.LoadingAnimation
import com.minroud.mortyverse.ui.containers.AsyncContent
import com.minroud.mortyverse.ui.containers.MortyverseScaffold
import com.minroud.mortyverse.ui.theme.MortyverseTheme
import com.minroud.mortyverse.ui.topbar.LocalTopBarTitleState
import com.minroud.mortyverse.ui.topbar.TopBar
import com.minroud.mortyverse.ui.topbar.TopBarButton
import com.minroud.mortyverse.ui.topbar.UpdateTopBarTitle
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun CharacterList(
    characters: LazyPagingItems<MortyverseCharacter>,
    topBarButton: TopBarButton,
    onCharacterSelected: (String) -> Unit,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier,
) {
    val lazyListState: LazyListState = rememberLazyListState()
    val bannerTranslationY by rememberTranslationY(lazyListState = lazyListState)
    val topBarVisibility by rememberVisibility(lazyListState = lazyListState)
    val homeTitle = stringResource(id = R.string.character_list_title)
    val refreshState = characters.loadState.refresh
    val refreshError = when (refreshState) {
        is LoadState.Error -> {
            val cause = refreshState.error
            (cause as? CharactersPagingException)?.domainError
                ?: com.minroud.mortyverse.domain.error.DomainError.Unknown(cause)
        }
        else -> null
    }

    UpdateTopBarTitle(title = homeTitle, enabled = refreshError == null)
    val currentTitle = LocalTopBarTitleState.current?.title ?: homeTitle

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.isNearEnd(3) }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                if (characters.loadState.append is LoadState.Error) {
                    characters.retry()
                }
            }
    }

    MortyverseScaffold(
        modifier = modifier,
        button = topBarButton,
        showTopBar = refreshError != null,
    ) { padding ->
        AsyncContent(
            isLoading = refreshState is LoadState.Loading,
            error = refreshError,
            modifier = Modifier.padding(padding),
        ) {
            LazyColumn(state = lazyListState) {
                stickyHeader {
                    TopBar(
                        button = topBarButton,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .graphicsLayer {
                                alpha = topBarVisibility
                            },
                        title = currentTitle,
                    )
                }
                item {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .zIndex(2f)
                            .graphicsLayer {
                                translationY = bannerTranslationY
                            },
                        painter = painterResource(id = R.drawable.characters_banner_heads_light),
                        contentDescription = stringResource(
                            id = R.string.characters_banner_heads_description,
                        ),
                        contentScale = ContentScale.FillWidth,
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                }
                items(count = characters.itemCount) { index ->
                    val item = characters[index] ?: return@items
                    CharacterCard(
                        character = item,
                        imageLoader = imageLoader,
                    ) {
                        onCharacterSelected(it)
                    }
                }
                if (characters.loadState.append is LoadState.Loading) {
                    item {
                        LoadingAnimation(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberTranslationY(lazyListState: LazyListState) =
    remember {
        derivedStateOf {
            val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
            val firstIndex = lazyListState.firstVisibleItemIndex
            val isBannerVisible = visibleItems.isNotEmpty() && (firstIndex == 0 || firstIndex == 1)
            if (isBannerVisible) {
                lazyListState.firstVisibleItemScrollOffset * .4f
            } else {
                0f
            }
        }
    }

@Composable
private fun rememberVisibility(lazyListState: LazyListState) =
    remember {
        derivedStateOf {
            val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
            val firstIndex = lazyListState.firstVisibleItemIndex
            val hasBannerItem = visibleItems.size > 1 && (firstIndex == 0 || firstIndex == 1)
            if (hasBannerItem) {
                val imageSize = visibleItems[1].size
                val scrollOffset = lazyListState.firstVisibleItemScrollOffset
                if (imageSize > 0) scrollOffset / imageSize.toFloat() else 1f
            } else {
                1f
            }
        }
    }

fun LazyListState.isNearEnd(threshold: Int): Boolean {
    val layoutInfo = layoutInfo
    val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return false
    val total = layoutInfo.totalItemsCount
    return lastVisible >= (total - threshold)
}

@Preview(showBackground = true)
@Composable
private fun CharacterListPreview() {
    val previewImageLoader = object : ImageLoader {
        @Composable
        override fun AsyncImage(
            model: String,
            contentDescription: String?,
            modifier: Modifier,
            contentScale: ContentScale?,
        ) = Box(modifier = modifier)

        @Composable
        override fun Gif(
            data: Any,
            contentDescription: String?,
            modifier: Modifier,
        ) = Box(modifier = modifier)
    }

    MortyverseTheme {
        val pagingItems = flowOf(
            PagingData.from(
                listOf(
                    MortyverseCharacter(
                        id = "1",
                        name = "Rick Sanchez",
                        species = "Human",
                        type = "",
                        status = "Alive",
                        image = "",
                    ),
                    MortyverseCharacter(
                        id = "2",
                        name = "Morty Smith",
                        species = "Human",
                        type = "",
                        status = "Alive",
                        image = "",
                    ),
                ),
            ),
        ).collectAsLazyPagingItems()
        CharacterList(
            characters = pagingItems,
            topBarButton = TopBarButton.Menu {},
            onCharacterSelected = {},
            imageLoader = previewImageLoader,
        )
    }
}
