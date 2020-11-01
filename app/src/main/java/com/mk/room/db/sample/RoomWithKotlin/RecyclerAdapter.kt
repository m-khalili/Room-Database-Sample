package com.mk.room.db.sample.RoomWithKotlin

import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.mk.room.db.sample.R
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerAdapter(val item: ArrayList<MainData>,val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val dataList = item[position]
        val  db = DatabaseRoom.getDatabase(context)
        holder.txt.setText(dataList.inputText)

        holder.deleteItem.setOnClickListener {
            val dataList1 = item[holder.adapterPosition]
            db.mainDao().delete(dataList1)
            item.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,item.size)
        }
        holder.editItem.setOnClickListener {
            val dataList2 = item[holder.adapterPosition]
            var id = dataList2.ID
            var sText = dataList2.inputText
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_edit)
            val width = WindowManager.LayoutParams.MATCH_PARENT
            val height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
            dialog.show()
            val editText = dialog.findViewById(R.id.edit_text) as EditText
            val update = dialog.findViewById(R.id.update) as Button

            editText.setText(sText)

            update.setOnClickListener {
                dialog.dismiss()
                val txt = editText.text.toString()
                db.mainDao().update(id,txt)
                item.clear()
                item.addAll(db.mainDao().getAll())
                notifyDataSetChanged()
            }

        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt = view.txt
        val deleteItem = view.delete
        val editItem = view.edit

   }

}