package com.minroud.mortyverse.ui.containers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minroud.mortyverse.ui.topbar.LocalTopBarTitleState
import com.minroud.mortyverse.ui.topbar.TopBar
import com.minroud.mortyverse.ui.topbar.TopBarButton
import com.minroud.mortyverse.ui.topbar.UpdateTopBarTitle

@Composable
fun MortyverseScaffold(
    button: TopBarButton,
    modifier: Modifier = Modifier,
    title: String? = null,
    showTopBar: Boolean = true,
    content: @Composable (PaddingValues) -> Unit,
) {
    UpdateTopBarTitle(title = title)
    val currentTitle = LocalTopBarTitleState.current?.title.orEmpty()
    val displayTitle = title ?: currentTitle

    Scaffold(
        modifier = modifier,
        topBar = {
            if (showTopBar) {
                TopBar(
                    button = button,
                    title = displayTitle,
                )
            }
        },
    ) { padding ->
        content(padding)
    }
}
