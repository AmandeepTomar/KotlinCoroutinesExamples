package com.amandeep.kotlincoroutinesexamples.coroutinechannels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

}

private fun CoroutineScope.produceNumbers() = produce<Int> {
    var x=1;
    while (true){
        send(x++)
        delay(100)
    }
}

fun CoroutineScope.launchProcessors(id: Int,channel: ReceiveChannel<Int>)=launch {
    for (message in channel){
        println("$id and messages $message")
    }
}

private fun CoroutineScope.square(number: ReceiveChannel<Int>)=produce<Int> {
    for(i in number)
        send(i * i)
}