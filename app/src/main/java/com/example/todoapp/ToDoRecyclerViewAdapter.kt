package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.to_do_item_row.view.*

class ToDoRecyclerViewAdapter(private var toDoObjects: ArrayList<ToDoList>):
RecyclerView.Adapter<ToDoRecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.to_do_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var toDoObject = toDoObjects[position]

        holder.itemView.apply {
            rvDoText.text = toDoObject.text
            rvCheckBok.isChecked = toDoObject.check
            // AlminPiricDojo code
            // https://github.com/AlminPiricDojo/ToDoApp/blob/master/app/src/main/java/com/example/todoapp/RVAdapter.kt
            rvCheckBok.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    rvDoText.setTextColor(Color.GRAY)
                }else{
                    rvDoText.setTextColor(Color.BLACK)
                }
                toDoObject.check = !toDoObject.check
            }

//            my code
//            rvCheckBok.setOnClickListener {
//                // if user click and the task was checked, so he wants to uncheck
//                if(toDoObject.check){
//                    // then, let state of box = uncheck(false)
//                    toDoObjects[position].check = false
//                    toDoObject.check = false
//                    // also, uncheck the box
//                    rvCheckBok.isChecked = false
//                    // and return the opacity of text
//                    rvDoText.setTextColor(Color.parseColor("#FF000000"))
//                }else{// if was uncheck, so he wants to check
//                    // then, let state of box check(true)
//                    toDoObjects[position].check = true
//                    toDoObject.check = true
//                    // also, check the box
//                    rvCheckBok.isChecked = true
//                    // and increase the opacity of the text
//                    rvDoText.setTextColor(Color.parseColor("#66000000"))
//                }
//                // after all, update
//                this@ToDoRecyclerViewAdapter.notifyDataSetChanged()
//            }
        }
    }

    override fun getItemCount() = toDoObjects.size

    // AlminPiricDojo code
    // https://github.com/AlminPiricDojo/ToDoApp/blob/master/app/src/main/java/com/example/todoapp/RVAdapter.kt
    fun deleteItems(){
        toDoObjects.removeAll{ item -> item.check }
        notifyDataSetChanged()
    }
}