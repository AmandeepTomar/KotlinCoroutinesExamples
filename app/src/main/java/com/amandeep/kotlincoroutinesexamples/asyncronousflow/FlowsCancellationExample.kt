package com.amandeep.kotlincoroutinesexamples.asyncronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
       val flow= flowCancellation()
            withTimeoutOrNull(2000){
                flow.collect {
                    print("$it ")
                }
            }

    }

}

 fun flowCancellation() = flow<Int> {
     listOf<Int>(1,2,3,4,5,6,7,8,90).forEach {
         delay(it*100L)
         emit(it)
     }
 }
