package com.example.coremvvmtest.sl

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface ProvideViewmodel {
    fun <T: ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner)
}