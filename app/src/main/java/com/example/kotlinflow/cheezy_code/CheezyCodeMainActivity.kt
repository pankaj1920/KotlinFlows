package com.example.kotlinflow.cheezy_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinflow.R
import com.example.kotlinflow.utils.Print
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class CheezyCodeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheezy_code_main)

        GlobalScope.launch {
            val data = producer()
            data.collect{
                Print.log("Print Flows ==>> $it")
            }
        }
    }

    fun producer () = flow<Int>{
        val list = listOf(1,2,3,4,5)
        list.forEach {
            delay(1000.milliseconds)
            emit(it)
        }
    }
}