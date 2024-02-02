package com.example.behrnintern.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.behrnintern.R
import com.example.behrnintern.data.TodiqTask

class TaskAdapter(private val taskList: MutableList<TodiqTask>) :
    RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)

        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = taskList[position]

        holder.taskTitle.text = currentItem.title
        holder.taskDescription.text = currentItem.description


    }

    override fun getItemCount(): Int {

        return taskList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDescription: TextView = itemView.findViewById(R.id.task_text)

    }


}