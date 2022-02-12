package com.amandeep.kotlincoroutinesexamples.coroutinesamples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    //jobExample()
    jobHierarchyExample()
}

private fun jobExample(){
    // its not print Start  Job1 ,because it cancel after 2000 and Job1 need 5000 to print Start Job 1
    println("Starting execution...")
    runBlocking {
        val job1=launch {
            delay(5000)
            println("Start Job1")
        }
        job1.invokeOnCompletion {
            println("Jon 1 completed")
        }


        delay(2000)
        println("We are cancelling the jon")
        job1.cancel()
    }
    println("End execution...")
}

private fun jobHierarchyExample(){
    /**
     * Starting execution...
    Start Job1
    Staring Job2...
    Job2 is finished
    Staring Job3...
    We are cancelling the jon
    job3 completed
    job2 completed
    Jon 1 completed
    End execution...
     * */
    println("Starting execution...")
    runBlocking {
        val job1=launch {
            println("Start Job1")
            val job2=launch {
                println("Staring Job2...")
                delay(3000)
                println("Job2 is finished")

                val job3=launch {
                    println("Staring Job3...")
                    delay(3000)
                    println("Job3 is finished")
                }
                job3.invokeOnCompletion { println("job3 completed") }
            }
            job2.invokeOnCompletion { println("job2 completed") }
        }
        job1.invokeOnCompletion {
            println("Jon 1 completed")
        }


        delay(6000)
        println("We are cancelling the jon")
        job1.cancel()
    }
    println("End execution...")
}