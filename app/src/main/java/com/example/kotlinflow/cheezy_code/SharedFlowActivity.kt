package com.example.kotlinflow.cheezy_code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflow.R
import com.example.kotlinflow.utils.Print
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SharedFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_flow)

        GlobalScope.launch {
            val result = producer()
            result.collect {
                Print.log(" Shared Flow 1 == Item - $it")
            }
        }
        GlobalScope.launch {
            val result = producer()
            delay(2500)
            result.collect {
                Print.log("Shared Flow 2 == Item - $it")
            }
        }
    }

    fun producer(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>(1)
        GlobalScope.launch {
            val list = listOf(1, 2, 3, 4, 5, 6, 7)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }

    }