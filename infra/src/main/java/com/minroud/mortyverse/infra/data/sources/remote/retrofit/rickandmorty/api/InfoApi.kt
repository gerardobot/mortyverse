package com.minroud.mortyverse.infra.data.sources.remote.retrofit.rickandmorty.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoApi(
    @SerialName("count") val characterCount: Int,
    @SerialName("pages") val pageCount: Int,
    @SerialName("next") val nextPage: String?,
    @SerialName("prev") val previousPage: String?
)
