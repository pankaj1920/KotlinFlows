package com.example.kotlinflow.cheezy_code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflow.R
import com.example.kotlinflow.utils.Print
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

class CheezyCodeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheezy_code_main)

        GlobalScope.launch {
            val data = producer()
            data.onStart {
                // yaha par hum emit hone se phele kuch task kar sakte hai ya koi aur data emit kra sakte hai
                // -1 humare list pe nhi hai but hum -1 emit krna chate hai flow emit hone se phele
                emit(-1)
                Print.log("Flow Starting")
            }.onCompletion {
                // yaha par hum emit hone ke baad kuch task kar sakte hai ya koi aur data emit kra sakte hai
                // 6 humare list pe nhi hai but hum 6 emit krna chate hai flow emit hone se baad
                emit(-1)
                Print.log("Flow Complete")
            }.onEach {
                Print.log("Flow About to emmit")
            }.collect {
                Print.log("Print Flows ==>> $it")
            }
        }

        GlobalScope.launch {
            //Terminal Operator

            // first will return only first item from flow item
            val dataFirsItem = producer().first()
            Print.log("Flow First Item $dataFirsItem")
            // it will convert flow into list
            val dataList = producer().toList()
            Print.log("Flow List $dataList")

        }

        GlobalScope.launch {
            producer().map {
                // yaha par hum flow item par kuch modification kar saket hai aur modified item hi hume collect par milenge
                // eg yaha par hum number ko 2 se multiply kar diya toh collect me jo bhi item milenge vo 2 se mutipy honge
                it * 2
            }.filter {
                // yaha par humbe ek condition pass krni h eg hume vhi item chaiye jo 8 se less hoe
                it < 8
            }.collect {

                Print.log("Flow Item $it")
            }
        }
        GlobalScope.launch {
            getNote().map {
                FormattedNote(it.isActive, it.title.uppercase(), it.description)
            }.filter {
                it.isActive
            }.collect {
                Print.log("Formatted Note")
                println(it)
            }
        }

        GlobalScope.launch {
            val time = measureTimeMillis {
                producer()
                    .buffer(3).collect {
                        delay(1500)
                        Print.log("Buuffer Flow $it")
                    }
            }
            Print.log("Buuffer Flow Time $time")
        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            delay(1000.milliseconds)
            emit(it)
        }
    }

    private fun getNote(): Flow<Note> {
        val list = listOf(
            Note(1, true, "First Note", "First Description"),
            Note(2, true, "Second Note", "Second Description"),
            Note(3, true, "Third Note", "Third Description"),
            Note(4, false, "Forth Note", "Forth Description")
        )
        return list.asFlow()
    }

    data class Note(val id: Int, val isActive: Boolean, val title: String, val description: String)
    data class FormattedNote(val isActive: Boolean, val title: String, val description: String)
}