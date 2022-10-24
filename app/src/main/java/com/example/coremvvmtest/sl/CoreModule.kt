package com.example.coremvvmtest.sl

import android.content.Context
import android.content.SharedPreferences
import android.view.Display.Mode
import androidx.viewbinding.BuildConfig
import com.example.coremvvmtest.core.Dispatchers
import com.example.coremvvmtest.core.ManageResources
import com.example.coremvvmtest.data.ProvideConverterFactory
import com.example.coremvvmtest.data.ProvideInterceptor
import com.example.coremvvmtest.data.ProvideOkttpClientBuilder
import com.example.coremvvmtest.data.ProvideRetrofitDBuilder
import com.example.coremvvmtest.presentation.GlobalErrorCommunication
import com.example.coremvvmtest.presentation.ProgressCommunication
import retrofit2.Retrofit

interface CoreModule: ManageResources, ProvideDispatchers, ProvideGlobalErrorCommunication,
                    ProvideProgressCommunication, ProvideRetrofitDBuilder, ProvideSharedPreferences{
        class Base(private val context: Context): CoreModule {
            private val dispatchers = Dispatchers.Base()
            private val manageResources = ManageResources.Base(context)

            private val errorCommunication = GlobalErrorCommunication.Base()
            private val progressCommunication = ProgressCommunication.Base()

            private val retrofitBuilder = ProvideRetrofitDBuilder.Base(
                ProvideConverterFactory.Base(),
                ProvideOkttpClientBuilder.Base(
                    if(BuildConfig.DEBUG)
                        ProvideInterceptor.Debug()
                    else
                        ProvideInterceptor.Release()
                ))
            override fun string(id: Int): String = manageResources.string(id)

            override fun dispatchers(): Dispatchers = dispatchers

            override fun globalErrorCommunication(): GlobalErrorCommunication.Mutable = errorCommunication

            override fun progressCommunication(): ProgressCommunication.Mutable = progressCommunication

            override fun provideRetrofitBuilder(): Retrofit.Builder = retrofitBuilder.provideRetrofitBuilder()

            override fun sharedPreferences(key: String): SharedPreferences =
                context.getSharedPreferences(key, Context.MODE_PRIVATE)

        }
}

interface ProvideDispatchers{
    fun dispatchers(): Dispatchers
}

interface ProvideGlobalErrorCommunication{
    fun globalErrorCommunication(): GlobalErrorCommunication.Mutable
}

interface ProvideProgressCommunication{
    fun progressCommunication(): ProgressCommunication.Mutable
}

interface ProvideSharedPreferences{
    fun sharedPreferences(key: String): SharedPreferences
}