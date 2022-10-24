package com.example.coremvvmtest.core

interface Matches<T> {
    fun matches(data: T): Boolean
}