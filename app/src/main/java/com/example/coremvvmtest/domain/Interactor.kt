package com.example.coremvvmtest.domain

import com.example.coremvvmtest.core.Dispatchers
import com.example.coremvvmtest.core.HandleError

interface Interactor {
    suspend fun <T> handle(
        successful: suspend (T) -> Unit,
        atFinish: suspend () -> Unit,
        block: suspend () -> T
    )

    class Abstract(
        private val dispatchers: Dispatchers,
        private val handleError: HandleError
    ): Interactor{
        override suspend fun <T> handle(
            successful: suspend (T) -> Unit,
            atFinish: suspend () -> Unit,
            block: suspend () -> T
        ) {
            try {
                val result = block.invoke()
                dispatchers.changeToUi { successful.invoke(result) }
            } catch (e: java.lang.Exception){handleError.handleError(e)}
            finally {
                dispatchers.changeToUi { atFinish.invoke() }
            }
        }
    }
}