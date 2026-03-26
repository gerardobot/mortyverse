package com.minroud.mortyverse.feature.characters.infra.remote.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationApi(
    @SerialName("name") val name: String,
)
