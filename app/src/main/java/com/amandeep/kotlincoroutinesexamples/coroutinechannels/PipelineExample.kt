package com.amandeep.kotlincoroutinesexamples.coroutinechannels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        val numbers=produceNumber()
       val squares= square(numbers)
         for(i in 1..6)
             println(squares.receive())
        println("Done fetching Square...")
        coroutineContext.cancelChildren()
    }
}


private fun CoroutineScope.produceNumber()=produce<Int> {
    var x=1;
    while (true)
        send(x++)
}

private fun CoroutineScope.square(number: ReceiveChannel<Int>)=produce<Int> {
    for (i in number)
        send(i * i)
}