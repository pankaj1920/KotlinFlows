package com.example.kotlinflow.utils

import android.util.Log


object Print {
    fun log(message: String?) {

            println("##################################")
            println("Print : $message")
            Log.e("Print", "Print : $message")


    }
}