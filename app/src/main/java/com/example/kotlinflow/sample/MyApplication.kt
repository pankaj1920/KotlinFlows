package com.example.kotlinflow.sample

import android.app.Application
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

       GlobalScope.launch {

       }

    }

    fun handleUncaughtException(thread: Thread?, e: Throwable) {

        e.printStackTrace() // not all Android versions will print the stack trace automatically
        println("Global Exception Message : ${e.message}")
        Toast.makeText(this, "Exception Message : ${e.message}", Toast.LENGTH_SHORT).show()
    }
}