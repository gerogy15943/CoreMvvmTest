package com.example.coremvvmtest.data

import retrofit2.Retrofit

interface ProvideRetrofitDBuilder {
    fun provideRetrofitBuilder(): Retrofit.Builder


    abstract class Abstract(
        private val provideConverterFactory: ProvideConverterFactory,
        private val httpClientBuilder: ProvideOkttpClientBuilder
    ): ProvideRetrofitDBuilder{
        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(provideConverterFactory.converterFactory())
            .client(httpClientBuilder.httpClientBuilder().build())
    }

    class Base(
        provideConverterFactory: ProvideConverterFactory,
        httpClientBuilder: ProvideOkttpClientBuilder
    ): Abstract(provideConverterFactory, httpClientBuilder)

}