package com.example.coremvvmtest.data

import com.example.coremvvmtest.core.HandleError

interface CloudDataSource {
    suspend fun <T> handle(block: suspend () -> T):  T

    abstract class Abstract(
        private val error: HandleError
    ) : CloudDataSource{
        override suspend fun <T> handle(block: suspend () -> T): T {
            try {
                block.invoke()
            } catch (e: java.lang.Exception){
                error.handleError(e)
            }
        }
    }
}