package com.minroud.mortyverse.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minroud.mortyverse.domain.result.error.DomainError
import com.minroud.mortyverse.ui.animations.LoadingAnimation
import com.minroud.mortyverse.ui.error.ErrorMessage

@Composable
fun StatefulContent(
    isLoading: Boolean,
    error: DomainError?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier) {
        when {
            error != null -> ErrorMessage(error = error)
            isLoading -> Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                LoadingAnimation()
            }
            else -> content()
        }
    }
}
