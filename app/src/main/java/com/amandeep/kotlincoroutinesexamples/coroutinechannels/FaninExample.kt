package com.amandeep.kotlincoroutinesexamples.coroutinechannels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel

fun main() {
    runBlocking {
        val channel= Channel<String>()
        sendMessages(channel,100L,"Message1")
        sendMessages(channel,200L,"Message2")
        sendMessages(channel,300L,"Message3")

        repeat(10){ println(channel.receive())}

        coroutineContext.cancelChildren()

    }

}

suspend fun CoroutineScope.sendMessages(channel:SendChannel<String>,delay: Long,message:String)=launch {
    while (true){
        delay(delay)
        channel.send(message)
    }
}