package com.example.freedidapp.utis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.behrnintern.R
import com.example.kotlintodopractice.utils.model.ToDoData

class TaskAdapter(private val userList: ArrayList<ToDoData>) :
    RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]

        holder.title.text = currentItem.title
        holder.description.text = currentItem.description


    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.task_title)
        val description: TextView = itemView.findViewById(R.id.task_text)

    }

}