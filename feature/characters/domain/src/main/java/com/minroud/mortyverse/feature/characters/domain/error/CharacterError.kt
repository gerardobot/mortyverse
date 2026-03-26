package com.minroud.mortyverse.feature.characters.domain.error

import com.minroud.mortyverse.domain.error.FeatureError

sealed interface CharacterError : FeatureError {
    data class InvalidId(
        override val cause: Throwable? = IllegalArgumentException(DEFAULT_MESSAGE),
    ) : CharacterError

    companion object {
        const val DEFAULT_MESSAGE = "Character id is null or empty"
    }
}
