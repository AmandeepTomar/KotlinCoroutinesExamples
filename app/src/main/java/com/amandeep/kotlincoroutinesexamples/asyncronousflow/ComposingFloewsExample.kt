package com.amandeep.kotlincoroutinesexamples.asyncronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


fun main() {
runBlocking {
    zipFlowExample()
    println("Combine two flows")
    combineFlow()
}

}

/**
 * Output
 *1->One
2->One
3->One
4->Two
4->Three
4->Four
 * */
suspend fun combineFlow() {
    val number=(1..4).asFlow().onEach { delay(300) }
    val english=flowOf("One","Two","Three","Four").onEach { delay(400) }

    number.combine(english){a,b->"$a->$b"}.collect { println(it) }
}
/**
 * Output
 *1 in english One
2 in english Two
3 in english Three
4 in english Four
 * */
suspend fun zipFlowExample(){
    val flow1=(1..4).asFlow()
    val secondFlow= flowOf("One","Two","Three","Four")
    flow1.zip(secondFlow){ a, b-> "$a in english $b"}.collect {  println(it) }
}