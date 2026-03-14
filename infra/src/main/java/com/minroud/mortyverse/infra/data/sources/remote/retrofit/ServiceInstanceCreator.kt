package com.minroud.mortyverse.infra.data.sources.remote.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

class ServiceInstanceCreator(
    private val context: Context
) {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    internal inline operator fun <reified T> invoke(
        baseUrl: String,
        interceptors: List<Interceptor> = listOf()
    ): T = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        client(getClient(interceptors))
    }.build().create(T::class.java)

    private fun getClient(interceptors: List<Interceptor>): OkHttpClient = OkHttpClient.Builder().apply {
        interceptors.forEach { addInterceptor(it) }
        addInterceptor(ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true).build())
        readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        writeTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
    }.build()

    companion object {
        private const val TIMEOUT_IN_SECONDS = 60
    }
}
