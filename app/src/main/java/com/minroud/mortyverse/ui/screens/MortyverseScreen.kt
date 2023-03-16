package com.minroud.mortyverse.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minroud.mortyverse.ui.theme.MortyverseTheme

@Composable
fun MortyverseScreen(content: @Composable () -> Unit) {
    MortyverseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
