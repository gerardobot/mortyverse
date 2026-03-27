package com.minroud.mortyverse.domain.error

sealed class DomainError(
    open val cause: Throwable?,
) {
    data class Unknown(
        override val cause: Throwable? = null,
    ) : DomainError(cause)

    data class Io(
        override val cause: Throwable? = null,
    ) : DomainError(cause)

    data class Network(
        override val cause: Throwable? = null,
    ) : DomainError(cause)

    data class Server(
        override val cause: Throwable? = null,
    ) : DomainError(cause)

    data class Feature(
        val featureError: FeatureError,
    ) : DomainError(featureError.cause)
}

interface FeatureError {
    val cause: Throwable?
}
