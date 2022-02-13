# Kotlin Coroutines 

## Introduction

- A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- A coroutine is an instance of suspendable computation.
- However, a coroutine is not bound to any particular thread. It may suspend its execution in one thread and resume in another one.
- Coroutines follow a principle of structured concurrency which means that new coroutines can be only launched in a specific CoroutineScope which delimits the lifetime of the coroutine.
- Its like we can run the coroutines in a thread so its is too fast , or we can run so many coroutines. 
- Many coroutines can be operated at the same time inside a thread. So its like many of coroutines you can host inside a thread.
- Simplify async code , callbacks and synchronisation.
- Coroutine can be pause or resume at any time.
- Coroutine shifted from one Thread to another thread when it resume.
### Features 
- <B>LightWeight</B>
    - You can run many coroutines on a single thread due to support for suspension, which doesn't block the thread where the coroutine is running. Suspending saves memory over blocking while supporting many concurrent operations.
- <B>Fewer memory leaks</B>
    - Use structured concurrency to run operations within a scope.
- <B>Built-in cancellation support</B>
- <B>Jetpack integration: </B>

### Basic Concepts 

#### </B>Scope</B>
- Create and run coroutines, provides lifecycle events like pause , resume and cancel.
- Allow Stop and start
- Are used to create and manage coroutine.  
- `GlobalScope.launch{}` 
    - it means the scope of this coroutine is entire application. 
    - If it is running in the background , it will not stop until the aap stop.
    - It is not used much
        ```kotlin
        GlobalScope.launch{
            println("GlobalScope")
        }

    ```
- `runBlocking{}` 
    - create a scope and run coroutine in a Blocking way. 
    - It is useful when you want to stop the execution of a code and run your coroutine in blocking way.
        ```kotlin
        runBlocking.launch{
            println("RunBlockingScope")
        }

    ```
- `CoroutineScope{}`
    - Create a scope does not complete until all children coroutines complete.
    - This coroutine is not complete until all the child coroutine get completed.
    ```kotlin
        CoroutineScope.launch{
            println("CoroutineScope")
        }

    ```
  
#### </B>Context</B>
- Scope provide a context in which the coroutine run.
- its simply a state of coroutine.
- Its set of dada that related to the coroutine , When a Scope create a coroutine that scope automatically provide a context for the coroutine.
- Set of variable and data associated with that coroutine.
- Context element 
  - Dispatches 
  - Jobs 
#### <B>Suspended function</B>
- it is a function that can be run in a coroutine , make callbacks seamless. like you can access local variable in function. 
- Function that can be run in a coroutine.
- Provide a functionality to run in parallel.
- we can update local variable example in file , just suspend in function to make function suspendable. 
#### <B>Jobs</B>
- its a handle on a coroutine , you can access lifecycle of coroutines.like cancel coroutine.
- `A.job()` will return Job.
- Job live in the hierarchy of other jobs , children and parent 
- ```kotlin
        Jo

   ```
- Access lifecycle variable and methods 
  - `cancel()` -> cancel the job. 
      - if Job is cancelled , All its parents and child job will be cancelled. 
  - `join()` -> wait until child coroutine completes
      - join the coroutine to the main thread basically (Need more clarification on this)
#### <B>Deferred</B>  
- A future result of a coroutine.
- 

#### <B>Dispatcher</B>   
- manages which thread the coroutine run on , on MainThread , on BackgroundThread 
- its basic on which thread the coroutine is run on. 
- `Dispatcher.Default`
    - CPU intensive work , Image process , data process and so on. 
- `Dispatcher.Main`
    - Where we can update UI , like in Android , we have update Ui on main thread.
    - Main Dispatches need to be defined in Gradle , it os done in Android Automatically. 
- `Dispatcher.IO`
    - Useful for Network communication to read and write request. 
- `Unconfined` 
    - Start the coroutine in the inherited dispatchers that called it. 
    - If you start coroutine with MainDispatchers and call another coroutine with Unconfined Dispatchers so it will be on Main Dispatchers.
- `newSingleThreadContext("MyThread")`  
    - Force creating of a new Thread.
    - This is not use much , because coroutine is so lightweight.
  
#### <B> Async </B>
- its another way to start coroutine. , its return the result.
- In the form of Deferred , Deferred its basically a promise that it will return After sometime and you will get a result , Just not available right now.
- When we need the result , call `await()` its a blocking call.
  - If the value is available it will return immediately.
  - If the value is not available it will pause the thread until it is.
  
#### <B>withContext</B>
- `withContext()`
    - its allow us to change context, basically switch between dispatchers , Like switch from IO to Main Dispatchers.
#### <B>Error handling</B>
- <B>Exception Handling </B>
  - As we have two different coroutine Builder `lunch` and `async`, 
  - `lunch` generate a Job and `async` generate a deferred.
    - lunch 
      - it has child-parent hierarchy.
      - Exception will be thrown immediately and job will fails.
      - use try/catch or an exception handling.
      - We can not directly see the exception we need to use `join on job`.
        ```kotlin

            import kotlin.coroutines.coroutineContext
            val exceptionHandler=CoroutineExceptionHandler{coroutineContext,throwable->
                // handle Exception 
            }
        
        ```
    - Async 
      - Exceptions are deferred until the result is consumed
      - If the result is not consumed the exception is never thrown 
      - try/catch in the coroutine or in the await() call.
  
### Asynchronous Flow
- Streams of values that are asynchronouslly computed. its live value after values.
- Flow emmit values , 
- `Flow{}` Builder 
- `emmit(value)` transmit values 
- `collect{}` Received the values , a flow can not emmit the values until , called collect.
````kotlin
      import kotlinx.coroutines.*
      import kotlinx.coroutines.flow.Flow
      import kotlinx.coroutines.flow.collect
      import kotlinx.coroutines.flow.flow

     fun sendPrimes(): Flow<Int> =
        flow<Int> {
            val primesList= listOf(2,3,5,7,11,13,19,23,29,31)
            primesList.forEach {
                delay(it*100L)
                emit(it)
            }
        }

    // Collect 
    fun main() {
      runBlocking {
        println("receiving Prime Numbers")
        sendPrimes().collect {
          println("Received prime no $it")
        }
      }
    }
````

#### <B>Creating Flow</B>
  - We can create flow by three methods 
    - ````kotlin
            flow{
                emit()
            }
      ````
    - ````kotlin
        listOf(1,2,3,4,5).asFlow()
      ```` 
     - using varargs methods of `FlowOf()`  `flowOf(10,20,30,"Aman","Data",10.4)
       `
#### <B>Properties</B>
  - The flow are cold that means the code does not run un till the collect function is called.
  - A flow can not be cancelled by it self.
  - it will be cancelled when the encompassing coroutine is cancelled., Flow is transparent for cancellation. 

#### <B>Operatoers</B>
  - take an input flow and transform into an output flow.
  - Operator are cold , still you need to call collect need to get.
  - The returning flow is synchronous 
      - That means it return immediatly its not a coroutine.
  - `map` Map a flow into anotheor flow.
  - ```kotlin
        (1..10).asFlow().map {
        "Mapping in to String $it"
        }.collect {
            println(it)
        }

    ```
   - `filter` -> filter the values that we get 
    - ````kotlin
         (1..10).asFlow().filter { it%2==0 }.collect { println(it) }
      ````
  - `Transform operator` -> Can emit any value at any time
    - Inside transform operator we can emit String and interger both the value at the same time
  ````kotlin
        (1..10).asFlow().transform{ emit("Strint $it.")emit(it) }.collect { println(it) }
````
#### <B>Buffereing</B>
#### <B>Composing Flows</B>
#### <B>Exception handling</B> 




  
