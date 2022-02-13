package com.amandeep.kotlincoroutinesexamples.coroutinechannels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("channelCreation")

    channelCreation()
    println("produceExample")

    produceExample()
    println("produceWithExtensionExample")
    produceWithExtensionExample()
    println("consumeEach")
    consumeEachExample()
}

fun consumeEachExample() {
    runBlocking {
        produceSquqres().consumeEach {
            print(it)
        }
    }
}

fun channelCreation(){
     runBlocking {
         val channel =Channel<Int>()
         launch {
             for (i in (1..5)) {
                 channel.send(i * i)
             }
             channel.close()
         }

         for (i in channel){
             println(i)
         }
     }
 }

fun produceExample(){
    runBlocking {
        val channel=produce<Int> {
            for (i in 1..10)
                channel.send(i)
        }
        for (i in channel)
            println(i)
    }
}

fun produceWithExtensionExample(){
    runBlocking {
        for (i in produceSquqres())
            println(i)
    }
}




// Its like extension function
fun CoroutineScope.produceSquqres()=produce<Int> {
    for (i in 1..6)
        this.send(i * i)
}



