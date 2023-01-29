package com.app.questionmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val value : String? = null
        val value2 : String? = "a"

        Log.d("MainActivity", value2!!)
        // 느낌표(!)는 null이 될 수 없는데 value2는 null이기 때문에 error 발생

    }
}