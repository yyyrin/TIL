package com.app.goodwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.AsyncListDiffer.ListListener

class SentenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence)

        val sentenceList = mutableListOf<String>()

        sentenceList.add("검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("사람에게 하나의 입과 두 개의 귀가 있는 것을 말하기보다 듣기를 두 배로 하라는 뜻이다.")
        sentenceList.add("결점이 없는 친구를 사귀려고 한다면 평생 친구를 가질 수 없을 것이다..")
        sentenceList.add("자기 아이에게 육체적 노동을 가르치지 않는 것은 약탈과 강도를 가르치는 것과 마찬가지다.")
        sentenceList.add("승자는 눈을 밟아 길을 만들지만 패자는 눈이 녹기를 기다린다.")
        sentenceList.add("두 개의 화살을 갖지 마라. 두 번째 화살이 있기 때문에 첫 번째 화살에 집중하지 않게 된다")
        sentenceList.add("그 사람 입장에 서기 전까지 절대 그 사람을 욕하거나 책망하지 마라.")
        sentenceList.add("뛰어난 말에게도 채찍이 필요하다.")

        // adapter에 data 넣기
        val sentenceAdapter = ListViewAdapter(sentenceList)
        val listview = findViewById<ListView>(R.id.sentenceListView)

        // listview로 넣기
       listview.adapter = sentenceAdapter

    }
}