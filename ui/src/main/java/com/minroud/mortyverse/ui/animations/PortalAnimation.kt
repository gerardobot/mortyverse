package com.minroud.mortyverse.ui.animations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minroud.mortyverse.ui.R
import com.minroud.mortyverse.infra.ui.loaders.ImageLoader
import org.koin.compose.koinInject

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = koinInject()
) = imageLoader.Gif(
    data = R.drawable.portal_animation,
    contentDescription = null,
    modifier = modifier
)
