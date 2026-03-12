package com.minroud.mortyverse.ui.error

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minroud.mortyverse.domain.result.error.DomainError

@Composable
fun ErrorMessage(error: DomainError, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        when (error) {
            is DomainError.CharacterDetail.InvalidId -> InvalidCharacterIdError()
            is DomainError.IoError, is DomainError.NetworkError -> NetworkError()
            is DomainError.Unknown -> UnknownError()
        }
    }
}
