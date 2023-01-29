package com.app.backbuttonex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 뒤로가기 버튼
   override fun onBackPressed() {
        Log.d("MainActivity", "backbutton")

        // 더블클릭 시, 종료하기
        if(isDouble == true) {
            finish()
        }

        isDouble = true
        Toast.makeText(this, "종료하실려면 더블클릭", Toast.LENGTH_LONG).show()

        // 2초 뒤에 isDouble 을 false로 바꿔준다.
        Handler().postDelayed(Runnable {
            isDouble = false
        }, 2000)
    }
}