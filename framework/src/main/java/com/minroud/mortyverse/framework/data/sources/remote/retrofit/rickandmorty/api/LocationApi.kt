package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationApi(
    @SerialName("name") val name: String
)
