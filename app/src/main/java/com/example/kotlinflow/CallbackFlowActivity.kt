package com.example.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CallbackFlowActivity : AppCompatActivity() {

    lateinit var arr: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback_flow)

        arr.get(0)
    }
}