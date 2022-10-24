package com.example.coremvvmtest.data

import okhttp3.logging.HttpLoggingInterceptor

interface ProvideInterceptor {
    fun interceptor(): HttpLoggingInterceptor

    abstract class Abstract(
        private val loginLevel: HttpLoggingInterceptor.Level
        ): ProvideInterceptor{
        override fun interceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = loginLevel
        }
    }

    class Debug: Abstract(HttpLoggingInterceptor.Level.BODY)
    class Release: Abstract(HttpLoggingInterceptor.Level.NONE)
}