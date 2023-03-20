package com.minroud.mortyverse.framework.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Gif {
    @Composable
    operator fun invoke(source: Any, contentDescription: String?, modifier: Modifier)
}
