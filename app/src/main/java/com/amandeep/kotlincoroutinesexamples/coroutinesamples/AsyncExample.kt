package com.amandeep.kotlincoroutinesexamples.coroutinesamples

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        val firstDeferred=async { getFirstNumber() }
        val secondDeferred=async { getSecondNumber() }
        val thirdDeferred=async { getThirdNumber() }

        println("Perform computation to get Numbers...")
        delay(1000)
        val first=firstDeferred.await()
        val second=secondDeferred.await()
        val third=thirdDeferred.await()

        println("Total is = ${first+second+third}")
    }
}

private suspend fun getFirstNumber():Int{
    delay(1000)
    val value=Random.nextInt(10)
    println("First random number is $value")
    return value
}

private suspend fun getSecondNumber():Int{
    delay(1500)
    val value=Random.nextInt(100)
    println("Second random number is $value")
    return value
}

private suspend fun getThirdNumber():Int{
    delay(2000)
    val value=Random.nextInt(1000)
    println("Third random number is $value")
    return value
}