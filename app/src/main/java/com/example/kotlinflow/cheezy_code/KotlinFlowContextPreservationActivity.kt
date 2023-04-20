package com.example.kotlinflow.cheezy_code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflow.R
import com.example.kotlinflow.utils.Print
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class KotlinFlowContextPreservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_flow_context_preservation)

        GlobalScope.launch(Dispatchers.Main) {
            producer()
                // agr hume producer koi aur thread pe execute krna hai toh hume flowOn ka use karenge
                // eg hum network request background thread pe execute karenge but data get hum main pe karenge
                // flow on se upper vale sare operator vo Dispatcher.IO pe exeute honge
                // eg map and filter flowOn ke upper likhe hai issslye ye Dispatcher.Io pe execute honge
                .map {
                    delay(2000)
                    Print.log("Map Collector Thread - ${Thread.currentThread().name}")
                    it * 2
                }
                .filter {
                    delay(500)
                    Print.log("Filter Collector Thread - ${Thread.currentThread().name}")
                    it < 8
                }
                .flowOn(Dispatchers.IO)
                //but isske neeche ke sare operator Main pe honge because humne uppr Dispatcher.Main kiya hai agr koi aur context hota toh flowOn ke neeche vale sare uss par hi execute hote
                .collect {
                    Print.log("Collector Thread - ${Thread.currentThread().name}")
                }
        }
    }


    fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            delay(1000.milliseconds)
            Print.log("Producer Thread - ${Thread.currentThread().name}")
            emit(it)

//            throw java.lang.Exception("Error in Emmiting")
        }

    }.catch {
        //Yaha par hum producer ke exception handle karenge
        Print.log("Emmiter Catch - ${it.message}")
    }


}