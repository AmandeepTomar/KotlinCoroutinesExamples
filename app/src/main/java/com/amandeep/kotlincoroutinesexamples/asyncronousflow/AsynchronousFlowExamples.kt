package com.amandeep.kotlincoroutinesexamples.asyncronousflow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    runBlocking {
//            println("Receiving Prime Numbers")
//            sendPrimes().collect {
//                print("Received prime no $it ")
//            }
//        println("Received Prime Numbers")


        createFlow().collect {
            print("$it ")
        }
        println()
        createFlow2().collect {
            print("$it ")
        }
        println()
        createFlow3UsingFloeOf().collect {
            print("$it ")
        }
        println()
    }
}

fun createFlow3UsingFloeOf() = flowOf(10,20,30,"Aman","Data",10.4)

fun createFlow2() = listOf("Hey","Hello","Hi").asFlow()

fun sendPrimes(): Flow<Int> =
    flow<Int> {
        val primesList= listOf(2,3,5,7,11,13,19,23,29,31)
        primesList.forEach {
            delay(it*100L)
            emit(it)
        }
    }

fun createFlow()= flow<Int> {
    for (i in 1..20)
        emit(i)
}



