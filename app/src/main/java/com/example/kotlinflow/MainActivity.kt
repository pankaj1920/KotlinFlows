package com.example.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        data()
        /*val data = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).flowOn(Dispatchers.IO)

        runBlocking {
            data.collect{
                Log.e("Main", "Data $it")
            }
        }*/

//        mapInFlow()


        filterInFlow()
    }

    fun filterInFlow() {
        val data = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 54, 78, 69, 1, 63, 89).flowOn(Dispatchers.IO)

        runBlocking {
            data.filter { value ->
                value % 2 == 0
            }.collect {
                Log.e("Main", "Data $it")
            }
        }


    }

    fun mapInFlow() {
        val data = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9).flowOn(Dispatchers.IO)

        runBlocking {
            data.map { value ->
                value * 2
            }.collect {
                Log.e("Main", "Data $it")
            }
        }
    }

    fun data() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                getData().catch {
                    Log.e("Main", "Exception ${it.message}")
                }.collect {
                    Log.e("Main", "Data $it")
                }
            }
        }
    }

    fun getData(): Flow<Int> = flow {
        for (i in 1..50) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)
}