package com.mk.room.db.sample.RoomWithKotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mk.room.db.sample.R
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainActivityKotlin : AppCompatActivity() {

    lateinit var listData : List<MainData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        val  db = DatabaseRoom.getDatabase(this)

        listData = db.mainDao().getAll()

        val adapter1 = RecyclerAdapter(listData as ArrayList<MainData>,this)
        recycler.adapter = adapter1


        add.setOnClickListener {
            val input: String =txt.text.toString()
            if (input.trim().length>0){
                val mainData = MainData(0,txt.text.toString())
                db.mainDao().insertAll(mainData)
                txt.setText("")
                (listData as ArrayList<MainData>).clear()
                (listData as ArrayList<MainData>).addAll( db.mainDao().getAll())

                adapter1.notifyDataSetChanged()
            }


     }

        removeAll.setOnClickListener {
            db.mainDao().deleteAll(listData)
            (listData as ArrayList<MainData>).clear()
            (listData as ArrayList<MainData>).addAll(db.mainDao().getAll())
            adapter1.notifyDataSetChanged()
        }

    }
}