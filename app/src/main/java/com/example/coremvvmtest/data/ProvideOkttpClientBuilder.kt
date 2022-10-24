package com.example.coremvvmtest.data

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkttpClientBuilder {
    fun httpClientBuilder(): OkHttpClient.Builder

    abstract class Abstract(
        private val provideInterceptor: ProvideInterceptor,
        private val timeOutSeconds: Long = 60L
    ): ProvideOkttpClientBuilder{
        override fun httpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor(provideInterceptor.interceptor())
                .connectTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .readTimeout(timeOutSeconds, TimeUnit.SECONDS)
    }

    class Base(provideInterceptor: ProvideInterceptor): Abstract(provideInterceptor)
}