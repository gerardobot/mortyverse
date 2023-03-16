package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationApi(
    @Json(name = "name") val name: String
)
