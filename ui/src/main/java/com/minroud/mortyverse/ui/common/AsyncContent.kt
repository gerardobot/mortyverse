package com.minroud.mortyverse.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.ui.animations.LoadingAnimation
import com.minroud.mortyverse.ui.error.ErrorMessage

@Composable
fun <T> AsyncContent(
    result: DomainResult<T>?,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit
) {
    Surface(modifier = modifier) {
        result?.let { result ->
            result.fold({ content(it) }) { ErrorMessage(error = it) }
        } ?: Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            LoadingAnimation()
        }
    }
}
