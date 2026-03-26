package com.minroud.mortyverse.ui.error

import androidx.compose.runtime.Composable
import com.minroud.mortyverse.domain.error.FeatureError

interface FeatureErrorRenderer {
    fun canRender(error: FeatureError): Boolean

    @Composable
    fun title(error: FeatureError): String? = null

    @Composable
    fun Render(error: FeatureError)
}
