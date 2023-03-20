package com.minroud.mortyverse.ui.animations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minroud.mortyverse.R
import com.minroud.mortyverse.framework.ui.image.Gif
import org.koin.androidx.compose.get

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    Gif: Gif = get()
) = Gif(
    source = R.drawable.portal_animation,
    contentDescription = null,
    modifier = modifier
)
