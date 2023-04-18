package com.example.kotlinflow.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflow.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {
    lateinit var abc: String
    lateinit var binding: ActivitySampleBinding
    var data: String = "Abc"
    val listData: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        data.toInt()
//        try {
//
//        } catch (ex:Exception) {
//            println("Caught exception")
//        }

        binding.click.setOnClickListener {
            listData!!.get(0)

        }
    }
}