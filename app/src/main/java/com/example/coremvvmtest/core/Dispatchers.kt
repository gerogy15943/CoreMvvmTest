package com.example.coremvvmtest.core

import kotlinx.coroutines.*

interface Dispatchers {
    fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    fun launchBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit)

    abstract class Abstract(
        private val ui: CoroutineDispatcher,
        private val background: CoroutineDispatcher) :
        Dispatchers {

        override fun launchUI(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ): Job  = scope.launch(ui, CoroutineStart.DEFAULT, block)

        override fun launchBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ): Job = scope.launch(background, CoroutineStart.DEFAULT, block)

        override suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit) {
            withContext(ui, block)
        }
    }
    class Base : Abstract(kotlinx.coroutines.Dispatchers.Main, kotlinx.coroutines.Dispatchers.IO)
}


