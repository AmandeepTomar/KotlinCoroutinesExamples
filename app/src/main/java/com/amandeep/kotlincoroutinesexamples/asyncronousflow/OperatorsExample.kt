package com.amandeep.kotlincoroutinesexamples.asyncronousflow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Map Operator")
        mapOperatorExample()
        println("Filter Operator")
        filterOperatorExample()
        println("Transform operator")
        transformOperatorExample()
        println("Transform Operator")
        takeOperator()
        println("Reduce Operator")
        reduceOperator()
    }

}

suspend fun reduceOperator() {
val size =5
    val factorial=(0..size).asFlow().reduce{
        accumulator, value ->
        accumulator*value
    }
    println("factorial is $factorial")

}


suspend fun takeOperator() {
    (1..10).asFlow().take(3).collect { print(it) }
}

/**
 * transform emit 20 values
 * */
suspend fun transformOperatorExample() {
    (1..10).asFlow().transform{
        emit("Strint $it ")
        emit(it)

    }.collect {
        println(it)
    }
}

suspend fun filterOperatorExample() {
    (1..10).asFlow().filter {
        it%2==0

    }.collect { println(it) }
}

suspend fun mapOperatorExample() {
    (1..10).asFlow().map {
        "Mapping in to String $it"
    }.collect {
        println(it)
    }
}
