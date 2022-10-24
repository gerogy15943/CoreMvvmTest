package com.example.coremvvmtest.data

interface MakeService {
    fun <T> service(clazz: Class<T>) : T

    abstract class Abstract(
        private val retrofitBuilder: ProvideRetrofitDBuilder
    ): MakeService{

        private val retrofit by lazy {
            retrofitBuilder.provideRetrofitBuilder()
                .baseUrl(baseUrl())
                .build()
        }
        override fun <T> service(clazz: Class<T>) = retrofit.create(clazz)

        protected open fun baseUrl(): String = "http://google.com"
    }
}