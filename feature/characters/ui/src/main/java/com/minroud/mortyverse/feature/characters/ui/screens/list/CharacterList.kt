package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.ui.R
import com.minroud.mortyverse.feature.characters.ui.screens.list.components.CharacterCard
import com.minroud.mortyverse.infra.ui.loaders.ImageLoader
import com.minroud.mortyverse.ui.animations.LoadingAnimation
import com.minroud.mortyverse.ui.containers.AsyncContent
import com.minroud.mortyverse.ui.containers.MortyverseScaffold
import com.minroud.mortyverse.ui.theme.MortyverseTheme
import com.minroud.mortyverse.ui.topbar.LocalTopBarTitleState
import com.minroud.mortyverse.ui.topbar.TopBar
import com.minroud.mortyverse.ui.topbar.TopBarButton
import com.minroud.mortyverse.ui.topbar.UpdateTopBarTitle

@Composable
internal fun CharacterList(
    state: CharacterListViewModel.State,
    topBarButton: TopBarButton,
    onCharacterSelected: (String) -> Unit,
    onScrollEnd: () -> Unit,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier,
) {
    val lazyListState: LazyListState = rememberLazyListState()
    val bannerTranslationY by rememberTranslationY(lazyListState = lazyListState)
    val topBarVisibility by rememberVisibility(lazyListState = lazyListState)
    val homeTitle = stringResource(id = R.string.character_list_title)

    UpdateTopBarTitle(title = homeTitle, enabled = state.error == null)
    val currentTitle = LocalTopBarTitleState.current?.title ?: homeTitle

    MortyverseScaffold(
        modifier = modifier,
        button = topBarButton,
        showTopBar = state.error != null,
    ) { padding ->
        AsyncContent(
            isLoading = state.isLoading,
            error = state.error,
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
                items(state.characterItems + null) { item ->
                    if (item == null && !state.isLastPage) {
                        LoadingAnimation(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                        )
                    } else if (item != null) {
                        CharacterCard(
                            character = item,
                            imageLoader = imageLoader,
                        ) {
                            onCharacterSelected(it)
                        }
                    }
                }
                item {
                    if (!state.isGettingNextCharacterPage && !state.isLastPage) {
                        LaunchedEffect(true) {
                            onScrollEnd()
                        }
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
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                    lazyListState.firstVisibleItemIndex == 0 ||
                    lazyListState.firstVisibleItemIndex == 1 -> {
                    lazyListState.firstVisibleItemScrollOffset * .4f
                }

                else -> {
                    0f
                }
            }
        }
    }

@Composable
private fun rememberVisibility(lazyListState: LazyListState) =
    remember {
        derivedStateOf {
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                    lazyListState.firstVisibleItemIndex == 0 ||
                    lazyListState.firstVisibleItemIndex == 1 -> {
                    val imageSize = lazyListState.layoutInfo.visibleItemsInfo[1].size
                    val scrollOffset = lazyListState.firstVisibleItemScrollOffset
                    scrollOffset / imageSize.toFloat()
                }

                else -> {
                    1f
                }
            }
        }
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
        CharacterList(
            state = CharacterListViewModel.State(
                characterItems = listOf(
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
                nextPage = 2,
            ),
            topBarButton = TopBarButton.Menu {},
            onCharacterSelected = {},
            onScrollEnd = {},
            imageLoader = previewImageLoader,
        )
    }
}
