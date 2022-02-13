package com.amandeep.kotlincoroutinesexamples.sharedstateproblem

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {

    sharedStateProblem()
    println("Atomic Solution ")
    atomicValueSolution()
    println("threadConfinementSolutionWithFineGrained")
    threadConfinementSolutionWithFineGrained()
    println("threadConfinementSolutionWithCoarseGrained")
    threadConfinementSolutionWithCoarseGrained()
    println("Mutex solution")
    mutexSolutionForShardProblem()
}

fun mutexSolutionForShardProblem() {
    runBlocking {
        val mutex= Mutex()
        var counter=0;
        withContext(Dispatchers.Default){
            massiveRun {
                mutex.withLock {
                    counter++
                }
            }

        }
        println("Counter =$counter")
    }
}

fun threadConfinementSolutionWithCoarseGrained() {
    // this one is fastest
    runBlocking {
        val counterContext= newSingleThreadContext("MyCounterContext")
        var counter=0
        withContext(counterContext){
            massiveRun { counter++ }
        }
        println("Counter =$counter")
    }
}


private fun threadConfinementSolutionWithFineGrained(){
    runBlocking {
        val counterContext= newSingleThreadContext("MyCounterContext")
        var counter=0
        withContext(Dispatchers.Default){
            massiveRun { withContext(counterContext){
                counter++
            } }
        }
        println("Counter =$counter")
    }
}

private fun atomicValueSolution(){
    runBlocking {
        val counter=AtomicInteger(0)
        withContext(Dispatchers.Default){
            massiveRun { counter.getAndIncrement() }
        }
        println("Counter =${counter.get()}")
    }
}

private fun sharedStateProblem() {
    runBlocking {
        var conter=0;
        withContext(Dispatchers.Default){
            massiveRun { conter++ }
        }
        println("Counter =$conter")
    }
}


private suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100;
    val k = 1000;
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}