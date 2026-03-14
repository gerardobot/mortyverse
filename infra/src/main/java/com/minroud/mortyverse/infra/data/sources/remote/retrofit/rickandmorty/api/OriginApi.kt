package com.minroud.mortyverse.infra.data.sources.remote.retrofit.rickandmorty.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginApi(
    @SerialName("name") val name: String
)
