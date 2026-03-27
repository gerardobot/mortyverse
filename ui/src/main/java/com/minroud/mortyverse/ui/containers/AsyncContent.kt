package com.minroud.mortyverse.ui.containers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minroud.mortyverse.domain.error.DomainError
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.ui.animations.LoadingAnimation
import com.minroud.mortyverse.ui.error.ErrorMessage

@Composable
fun AsyncContent(
    isLoading: Boolean,
    error: DomainError?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(modifier = modifier) {
        when {
            error != null -> ErrorMessage(error = error)
            isLoading -> LoadingSurface()
            else -> content()
        }
    }
}

@Composable
fun <T> AsyncContent(
    result: DomainResult<T>?,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    Surface(modifier = modifier) {
        when (result) {
            null -> LoadingSurface()
            is DomainResult.Success -> content(result.data)
            is DomainResult.Error -> ErrorMessage(error = result.error)
        }
    }
}

@Composable
private fun LoadingSurface() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        LoadingAnimation()
    }
}
