package com.minroud.mortyverse.ui.screens.character.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.minroud.mortyverse.R
import com.minroud.mortyverse.ui.animations.LoadingAnimation
import com.minroud.mortyverse.ui.common.StatefulContent
import com.minroud.mortyverse.ui.error.ErrorMessage
import com.minroud.mortyverse.ui.navigation.DrawerContent
import com.minroud.mortyverse.ui.navigation.DrawerOption
import com.minroud.mortyverse.ui.navigation.TopBar
import com.minroud.mortyverse.ui.navigation.TopBarButton
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharacterList(
    topBarButton: TopBarButton,
    drawerOptions: List<DrawerOption>,
    drawerState: DrawerState,
    onCharacterSelected: (String) -> Unit,
    viewModel: CharacterListViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val bannerTranslationY by rememberTranslationY(lazyListState = lazyListState)
    val topBarVisibility by rememberVisibility(lazyListState = lazyListState)

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.primary,
        drawerContent = {
            DrawerContent(drawerOptions = drawerOptions)
        },
        content = {
            if (state.error != null) {
                Scaffold(
                    topBar = {
                        TopBar(
                            button = topBarButton,
                            title = stringResource(id = R.string.top_bar_title_home)
                        )
                    }
                ) { padding ->
                    ErrorMessage(error = state.error!!, modifier = Modifier.padding(padding))
                }
            } else {
                StatefulContent(isLoading = state.isLoading, error = state.error) {
                    LazyColumn(state = lazyListState) {
                        stickyHeader {
                            TopBar(
                                button = topBarButton,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                                    .graphicsLayer {
                                        alpha = topBarVisibility
                                    },
                                title = stringResource(id = R.string.top_bar_title_home)
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

                                painter = painterResource(id = R.drawable.banner_show_me_what_you_got_heads_light),
                                contentDescription = stringResource(id = R.string.banner_show_me_what_you_got_description),
                                contentScale = ContentScale.FillWidth
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
                                        .padding(bottom = 8.dp)
                                )
                            } else if (item != null) {
                                CharacterCard(character = item) {
                                    onCharacterSelected(
                                        it
                                    )
                                }
                            }
                        }
                        item {
                            if (!state.isGettingNextCharacterPage && !state.isLastPage) {
                                LaunchedEffect(true) {
                                    viewModel.onScrollEnd()
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun rememberTranslationY(lazyListState: LazyListState) = remember {
    derivedStateOf {
        when {
            lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0 || lazyListState.firstVisibleItemIndex == 1 ->
                lazyListState.firstVisibleItemScrollOffset * .4f
            else -> 0f
        }
    }
}

@Composable
private fun rememberVisibility(lazyListState: LazyListState) = remember {
    derivedStateOf {
        when {
            lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0 || lazyListState.firstVisibleItemIndex == 1 -> {
                val imageSize = lazyListState.layoutInfo.visibleItemsInfo[1].size
                val scrollOffset = lazyListState.firstVisibleItemScrollOffset
                scrollOffset / imageSize.toFloat()
            }
            else -> 1f
        }
    }
}
