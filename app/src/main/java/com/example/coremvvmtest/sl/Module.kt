package com.example.coremvvmtest.sl

interface Module<T> {
    fun viewModel(): T
}