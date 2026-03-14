package com.minroud.mortyverse.infra.remote.retrofit

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

fun createRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    json: Json
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()

inline fun <reified T> Retrofit.createService(): T =
    create(T::class.java)
