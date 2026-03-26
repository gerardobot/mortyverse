package com.minroud.mortyverse.ui.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import org.koin.compose.getKoin

val LocalFeatureErrorRenderers = staticCompositionLocalOf<List<FeatureErrorRenderer>> { emptyList() }

@Composable
fun ProvideFeatureErrorRenderers(
    renderers: List<FeatureErrorRenderer>,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalFeatureErrorRenderers provides renderers) {
        content()
    }
}

@Composable
fun rememberFeatureErrorRenderersForScreen(): List<FeatureErrorRenderer> {
    val koin = getKoin()
    return remember(koin) {
        runCatching { koin.getAll<FeatureErrorRenderer>() }.getOrDefault(emptyList())
    }
}
