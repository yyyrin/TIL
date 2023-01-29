package com.app.listview_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_item = mutableListOf<ListViewModel>()

        list_item.add(ListViewModel("a", "b"))
        list_item.add(ListViewModel("c", "d"))
        list_item.add(ListViewModel("e", "f"))

        // activity_main.xml 에 있는 listview 가져오기
        val listview = findViewById<ListView>(R.id.mainListview)

        // Adapter로 data 전달
        val listAdapter = ListViewAdapter(list_item)
        // listview에 있는 adapter에 listAdapter 연결
       listview.adapter = listAdapter
    }
}