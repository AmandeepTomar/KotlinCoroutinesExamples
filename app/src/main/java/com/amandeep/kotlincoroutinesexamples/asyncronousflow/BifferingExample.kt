package com.amandeep.kotlincoroutinesexamples.asyncronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            floWWithDelay().collect {
                delay(300)
                println(it)
            }
        }
        println("Time measured in milli without buffer $time")

        val bufferTime= measureTimeMillis {
            floWWithDelay().buffer().collect {
                delay(300)
                print(it)
            }
        }
        println("Time measured in milli with buffer $bufferTime")

    }


}

 fun floWWithDelay()= flow<Int> {
     (1..5).forEach {
         delay(100)
         emit(it)
     }
 }




