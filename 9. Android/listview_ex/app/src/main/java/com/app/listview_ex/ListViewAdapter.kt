package com.app.listview_ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val List : MutableList<ListViewModel>) : BaseAdapter(){
    // item들의 개수
    override fun getCount(): Int {
        return List.size
    }

    // 특별한 것 x, 그냥 이렇게 사용한다고 알아두기
    override fun getItem(position: Int): Any {
        return List[position]
    }

    // 특별한 것 x, 그냥 이렇게 사용한다고 알아두기
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var converView = convertView

        if (converView == null) {
            // listview_item.xml 에서 item 가져오기
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val title = converView!!.findViewById<TextView>(R.id.listviewItem)
        val content = converView!!.findViewById<TextView>(R.id.listviewItem2)
        // titie의 text를 list 안의 각각의 item들로 연결해줘라
        title.text = List[position].title
        content.text = List[position].content
        // List[0] -> ListViewModel("a", "b")
        // List[1] -> ListViewModel("c", "d")
        // List[2] -> ListViewModel("e", "f")

        return converView!!
    }
}