package com.minroud.mortyverse.ext

import androidx.annotation.StringRes
import com.minroud.mortyverse.R
import com.minroud.mortyverse.domain.result.error.DomainError

val DomainError.titleAsStringRes get(): @StringRes Int = when (this) {
    is DomainError.CharacterDetail.InvalidId -> R.string.error_invalid_character_id_title
    is DomainError.NetworkError -> R.string.error_network_title
    is DomainError.IoError, is DomainError.Unknown -> R.string.error_unknown_title
}
