package com.example.coremvvmtest.core

interface HandleError {
    fun handleError(error: Exception): Exception
}