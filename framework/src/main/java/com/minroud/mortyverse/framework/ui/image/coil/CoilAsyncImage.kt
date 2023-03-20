package com.minroud.mortyverse.framework.ui.image.coil

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.minroud.mortyverse.framework.ui.image.AsyncImage

class CoilAsyncImage : AsyncImage {
    @Composable
    override fun invoke(
        source: String,
        contentDescription: String?,
        modifier: Modifier,
        contentScale: ContentScale?
    ) {
        coil.compose.AsyncImage(
            model = source,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale ?: ContentScale.Fit
        )
    }
}
