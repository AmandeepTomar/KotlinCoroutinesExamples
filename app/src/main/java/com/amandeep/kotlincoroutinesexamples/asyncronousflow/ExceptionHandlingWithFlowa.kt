package com.amandeep.kotlincoroutinesexamples.asyncronousflow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() {
    runBlocking {
        println("Try/Catch Operator")
        tryCatch()
        println("catch Operator")
        catchOperator()
        println("onCompletion Operator")
        onCompletionOperator()
    }

}

suspend fun onCompletionOperator() {
    (1..10).asFlow()
        .onEach { check(it!=12) }
        .onCompletion {  cause ->
            if (cause!=null)
                println("Exception occurs $cause")
            else
                println("FLow complete without exception")
        }
        .catch { println("Catch exception $it") }
        .collect { println(it) }
}

suspend fun catchOperator(){
    (1..10).asFlow().onEach { check(it!=2) }.catch { println("Exception $it") }.collect { print(it) }
}

suspend fun tryCatch() {
    try {
        (1..10).asFlow().onEach { check(it != 8) }.collect { print(it) }
    }catch (e:Exception){
        println("Exception is $e")
    }
}
