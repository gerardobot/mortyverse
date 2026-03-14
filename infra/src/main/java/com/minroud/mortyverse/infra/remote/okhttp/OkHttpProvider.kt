package com.minroud.mortyverse.infra.remote.okhttp

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val DEFAULT_TIMEOUT_SECONDS = 60L

fun createOkHttpClient(context: Context): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .alwaysReadResponseBody(true)
                .build()
        )
        .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()
