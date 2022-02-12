package com.amandeep.kotlincoroutinesexamples.coroutinesamples

import kotlinx.coroutines.*
import java.lang.Exception

fun main() {

    needJoinJobToThrowException()
}

/**
 * If we do not join job then nothing will be thrown.
 * */
fun needJoinJobToThrowException() {

    runBlocking {
        val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("We get the exception $throwable")
        }

        val job1 = GlobalScope.launch(myHandler) {
            println("Job1 throwing exception")
            throw ArrayIndexOutOfBoundsException()
        }
        job1.join()

        val deferred=GlobalScope.async {
            println("Deferred throwing Exception")
            throw ArrayIndexOutOfBoundsException()
        }
        try {
            deferred.await()
        }catch (e:Exception){
            println("Deferred Exception : $e")
        }

    }
}
