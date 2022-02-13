package com.amandeep.kotlincoroutinesexamples.coroutinechannels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel= Channel<Int>(2)
        val sender=launch {
            repeat(10){
                channel.send(it)
                println("Channel Add value $it")
            }

        }

        for (i in channel){
            delay(1000)
            println("Received value $i")
        }
        sender.cancel()

    }
}