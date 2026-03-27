package com.minroud.mortyverse.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minroud.mortyverse.ui.error.ProvideFeatureErrorRenderers
import com.minroud.mortyverse.ui.error.rememberFeatureErrorRenderersForScreen
import com.minroud.mortyverse.ui.theme.MortyverseTheme
import com.minroud.mortyverse.ui.topbar.ProvideTopBarTitleState

@Composable
fun MortyverseRoot(content: @Composable () -> Unit) {
    MortyverseTheme {
        ProvideFeatureErrorRenderers(renderers = rememberFeatureErrorRenderersForScreen()) {
            ProvideTopBarTitleState {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    content()
                }
            }
        }
    }
}
