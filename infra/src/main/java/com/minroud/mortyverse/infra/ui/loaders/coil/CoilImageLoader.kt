package com.minroud.mortyverse.infra.ui.loaders.coil

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.minroud.mortyverse.infra.ui.loaders.ImageLoader

class CoilImageLoader : ImageLoader {
    @Composable
    override fun AsyncImage(
        model: String,
        contentDescription: String?,
        modifier: Modifier,
        contentScale: ContentScale?,
    ) {
        coil.compose.AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale ?: ContentScale.Fit,
        )
    }

    @Composable
    override fun Gif(
        data: Any,
        contentDescription: String?,
        modifier: Modifier,
    ) {
        val imageLoader = coil.ImageLoader.Builder(LocalContext.current)
            .components { add(ImageDecoderDecoder.Factory()) }
            .build()

        Image(
            modifier = modifier,
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = data)
                    .apply(block = fun ImageRequest.Builder.() {
                        size(Size.ORIGINAL)
                    }).build(),
                imageLoader = imageLoader,
            ),
            contentDescription = contentDescription,
        )
    }
}
