package com.amandeep.kotlincoroutinesexamples

import kotlinx.coroutines.*

var functionCalls=0;

fun main() {
    lunchFirstCoroutine() // output -> Hello , Coroutine...
// its like this because  launch coroutine and delay for 1000 and print Hello and Thread wait for 2000 and
// then it came to coroutine after 1000  and print coroutine....

// if we use delay(3000) that ius greater than Thread.sleep(2000) so it print only Hello because after 2000 it will return from main.

    coroutineScopes()
    println()

    // suspend function example
    GlobalScope.launch {
        launch {
            coroutineSuspendedExample()
        }
        GlobalScope.launch {
            coroutineSuspendFunction()
        }
    }
    print("Hello...")
    Thread.sleep(2000)
    println("we have call functionCalls $functionCalls times")



}


private suspend fun coroutineSuspendedExample(){
    delay(500)
    println("Suspended function")
    functionCalls++
}

private suspend fun coroutineSuspendFunction(){
    delay(1500)
    println("Suspended function 2")
    functionCalls++
}


private fun coroutineContextExample(){
    runBlocking {
        launch(CoroutineName("Hey this one in mine")) {
            println("Please print name of coroutine ${coroutineContext[CoroutineName.Key]}")
        }
    }
}

private fun coroutineScopes(){
    println("Start Run blocking scope , its blocking the execution..")
    runBlocking {
        launch {
            delay(2000)
            println("RunBlocking scope execute")
        }

        coroutineScope {
            launch {
                delay(700)
                println("Coroutine Scope executed")
            }
        }

        GlobalScope.launch {
            delay(1000)
            println("GlobalScope is execute")
        }
    }
    print("End Run blocking")
}

private fun lunchFirstCoroutine(){
    GlobalScope.launch {
        delay(1000)
        print("Coroutine...")

    }
    print("Hello ")
    // we can not run delay from out side of coroutine scope
   // delay(1000)
    Thread.sleep(2000)
    println()
}
