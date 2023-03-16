package com.minroud.mortyverse.domain.result.error

sealed class DomainError(
    open val cause: Throwable?
) {
    data class Unknown(override val cause: Throwable? = null) : DomainError(cause)

    data class IoError(override val cause: Throwable? = null) : DomainError(cause)

    data class NetworkError(override val cause: Throwable? = null) : DomainError(cause)

    sealed class CharacterDetail(override val cause: Throwable? = null) : DomainError(cause) {
        data class InvalidId(override val cause: Throwable? = null) : CharacterDetail(cause)
    }
}
