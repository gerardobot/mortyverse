package com.minroud.mortyverse.ui.ext

import com.minroud.mortyverse.domain.result.error.DomainError
import com.minroud.mortyverse.ui.R

@get:androidx.annotation.StringRes
val DomainError.titleAsStringRes: Int
    get() = when (this) {
    is DomainError.CharacterDetail.InvalidId -> R.string.error_invalid_character_id_title
    is DomainError.NetworkError -> R.string.error_network_title
    is DomainError.IoError, is DomainError.Unknown -> R.string.error_unknown_title
}
