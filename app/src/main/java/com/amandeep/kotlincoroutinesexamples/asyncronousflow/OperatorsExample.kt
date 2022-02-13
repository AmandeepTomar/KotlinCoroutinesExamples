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
    }

}

/**
 * transform emit 20 values
 * */
suspend fun transformOperatorExample() {
    (1..10).asFlow().transform{
        emit("Strint $it.")
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
