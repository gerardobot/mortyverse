package com.minroud.mortyverse.ui.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
class TopBarTitleState internal constructor(
    initialTitle: String,
) {
    private val _title: MutableState<String> = mutableStateOf(initialTitle)
    val title: String get() = _title.value

    fun setTitle(title: String) {
        _title.value = title
    }
}

val LocalTopBarTitleState = staticCompositionLocalOf<TopBarTitleState?> { null }

@Composable
fun ProvideTopBarTitleState(
    initialTitle: String = "",
    content: @Composable () -> Unit,
) {
    val state = remember { TopBarTitleState(initialTitle) }
    CompositionLocalProvider(LocalTopBarTitleState provides state) {
        content()
    }
}

@Composable
fun UpdateTopBarTitle(
    title: String?,
    enabled: Boolean = true,
) {
    if (!enabled) return
    val titleValue = title?.takeIf { it.isNotEmpty() } ?: return
    val state = LocalTopBarTitleState.current ?: return
    SideEffect {
        state.setTitle(titleValue)
    }
}
