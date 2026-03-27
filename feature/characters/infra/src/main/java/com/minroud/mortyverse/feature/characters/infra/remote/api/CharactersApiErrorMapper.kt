package com.minroud.mortyverse.feature.characters.infra.remote.api

import com.minroud.mortyverse.domain.error.DomainError
import com.minroud.mortyverse.feature.characters.domain.error.CharacterError
import retrofit2.HttpException
import java.io.IOException

fun Exception.toDomainError() =
    when (this) {
        is HttpException -> this.toDomainError()
        is IOException -> DomainError.Io(this)
        else -> DomainError.Unknown(this)
    }

fun HttpException.toDomainError() =
    when (code()) {
        404 -> DomainError.Feature(CharacterError.InvalidId(this))
        in 500..599 -> DomainError.Server(this)
        else -> DomainError.Network(this)
    }
