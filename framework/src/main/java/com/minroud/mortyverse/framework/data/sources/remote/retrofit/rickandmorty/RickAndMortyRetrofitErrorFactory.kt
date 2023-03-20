package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty

import com.minroud.mortyverse.domain.result.error.DomainError
import retrofit2.HttpException
import java.io.IOException

fun Exception.toDomainError() = when (this) {
    is HttpException -> this.toDomainError()
    is IOException -> DomainError.IoError(this)
    else -> DomainError.Unknown(this)
}

fun HttpException.toDomainError() = when (code()) {
    404 -> DomainError.CharacterDetail.InvalidId(this)
    else -> DomainError.NetworkError(this)
}
