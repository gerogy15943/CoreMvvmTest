package com.example.coremvvmtest.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T>: MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        if(mPending.compareAndSet(true, false))
            observer.onChanged(T)
    }

    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }
}