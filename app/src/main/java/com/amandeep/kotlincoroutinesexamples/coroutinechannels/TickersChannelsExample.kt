package com.amandeep.kotlincoroutinesexamples.coroutinechannels

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val ticker= ticker(100,0)
        launch {
            val start=System.currentTimeMillis()

            ticker.consumeEach {
                val delta=System.currentTimeMillis()-start
                println("received tick after $delta")
            }
        }

        delay(1000)
        ticker.cancel()
    }
}