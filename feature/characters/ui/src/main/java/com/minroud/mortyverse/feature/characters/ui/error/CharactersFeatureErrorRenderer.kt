package com.minroud.mortyverse.feature.characters.ui.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.minroud.mortyverse.domain.error.FeatureError
import com.minroud.mortyverse.feature.characters.domain.error.CharacterError
import com.minroud.mortyverse.feature.characters.ui.R
import com.minroud.mortyverse.ui.error.FeatureErrorRenderer

internal class CharactersFeatureErrorRenderer : FeatureErrorRenderer {
    override fun canRender(error: FeatureError): Boolean = error is CharacterError

    @Composable
    override fun title(error: FeatureError): String? =
        when (error) {
            is CharacterError.InvalidId -> stringResource(id = R.string.error_invalid_character_id_title)
            else -> null
        }

    @Composable
    override fun Render(error: FeatureError) {
        when (error) {
            is CharacterError.InvalidId -> InvalidCharacterIdError()
        }
    }
}
