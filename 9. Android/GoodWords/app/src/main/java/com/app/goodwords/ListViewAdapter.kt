package com.app.goodwords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.databinding.adapters.Converters

// 문자열 data로 이루어진 list 받기
class ListViewAdapter(val List : MutableList<String>) : BaseAdapter() {

    // 전체 list의 크기
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Adapter로 넘어온 data를 listview_item으로 넣어주기
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        // 비어있으면 가져오기
       if (convertView == null) {
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val listviewText = convertView?.findViewById<TextView>(R.id.listViewTextArea)
        listviewText!!.text = List[position]

        return convertView!!

    }
}