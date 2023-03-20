package com.minroud.mortyverse.framework.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

interface AsyncImage {
    @Composable
    operator fun invoke(
        source: String,
        contentDescription: String?,
        modifier: Modifier,
        contentScale: ContentScale?
    )
}
