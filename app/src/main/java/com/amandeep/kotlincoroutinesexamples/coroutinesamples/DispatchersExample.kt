package com.amandeep.kotlincoroutinesexamples.coroutinesamples

import kotlinx.coroutines.*

fun main() {
   // dispatchersExample()
    switchDispatchersExample()
}
/**
 * Default and Last is same.
 * Default context [StandaloneCoroutine{Active}@e7e1ee8, Dispatchers.Default]
    Unconfined context [StandaloneCoroutine{Active}@3cd60fd, Dispatchers.Unconfined]
    Last context [StandaloneCoroutine{Active}@e7e1ee8, Dispatchers.Default]
    IO context [StandaloneCoroutine{Active}@45554563, Dispatchers.IO]
 *
 * */
fun switchDispatchersExample() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("Default context $coroutineContext")
            launch(Dispatchers.IO) {
                println("IO context $coroutineContext")
            }
            launch(Dispatchers.Unconfined) {
                println("Unconfined context $coroutineContext")
            }
            println("Last context $coroutineContext")

        }
    }
}

/**
 * OutPut
 * Dispatchers.Default : Thread Name: DefaultDispatcher-worker-1
Dispatchers.IO : Thread Name: DefaultDispatcher-worker-2
Dispatchers.Unconfined : Thread Name: main
newSingleThreadContext : Thread Name: MyCustomThread
Dispatchers.Default : Thread Name: DefaultDispatcher-worker-1
Dispatchers.IO : Thread Name: DefaultDispatcher-worker-2
Dispatchers.Unconfined : Thread Name: kotlinx.coroutines.DefaultExecutor
newSingleThreadContext : Thread Name: MyCustomThread
 * */
fun dispatchersExample() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("Dispatchers.Default : Thread Name: ${Thread.currentThread().name} ")
            delay(100)
            println("Dispatchers.Default : Thread Name: ${Thread.currentThread().name} ")
        }

        // error in Android
     //   Exception in thread "main" java.lang.IllegalStateException: Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used
//        launch(Dispatchers.Main) {
//            println("Dispatchers.Main : Thread Name: ${Thread.currentThread().name} ")
//            delay(100)
//            println("Dispatchers.Main : Thread Name: ${Thread.currentThread().name} ")
//        }

        launch(Dispatchers.IO) {
            println("Dispatchers.IO : Thread Name: ${Thread.currentThread().name} ")
            delay(100)
            println("Dispatchers.IO : Thread Name: ${Thread.currentThread().name} ")
        }

        launch(Dispatchers.Unconfined) {
            println("Dispatchers.Unconfined : Thread Name: ${Thread.currentThread().name} ")
            delay(100)
            println("Dispatchers.Unconfined : Thread Name: ${Thread.currentThread().name} ")
        }

        launch(newSingleThreadContext("MyCustomThread")) {
            println("newSingleThreadContext : Thread Name: ${Thread.currentThread().name} ")
            delay(100)
            println("newSingleThreadContext : Thread Name: ${Thread.currentThread().name} ")
        }

    }
}
