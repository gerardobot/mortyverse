package com.minroud.mortyverse.framework.data.sources.remote.retrofit.rickandmorty.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoApi(
    @Json(name = "count") val characterCount: Int,
    @Json(name = "pages") val pageCount: Int,
    @Json(name = "next") val nextPage: String?,
    @Json(name = "prev") val previousPage: String?
)
