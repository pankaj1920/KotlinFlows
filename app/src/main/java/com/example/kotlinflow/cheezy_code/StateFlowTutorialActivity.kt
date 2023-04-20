package com.example.kotlinflow.cheezy_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinflow.R
import com.example.kotlinflow.utils.Print
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateFlowTutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow_tutorial)

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
/*            delay(8000)
            result.collect{
                Print.log("Collect 1 = $it")
            }*/
            Print.log("Collect 1 = ${result.value}")
        }
    }


    fun producer(): StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {

            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)

        }
        return mutableStateFlow
    }

}