package com.example.behrnintern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.behrnintern.databinding.TaskBinding
import com.example.kotlintodopractice.utils.model.ToDoData

class TodoAdapter(private val list: MutableList<ToDoData>) :
    RecyclerView.Adapter<TodoAdapter.TaskViewHolder>() {

    private var listener: TodoAdapterClicksInterface? = null
    fun setListener(listener: TodoAdapterClicksInterface) {
        this.listener = listener
    }

    inner class TaskViewHolder(val binding: TaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val binding = TaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.taskText.text = this.description
                binding.taskTitle.text = this.title


                binding.delete.setOnClickListener {
                    listener?.onDeleteTaskButtonClick(this)

                }

                binding.edit.setOnClickListener {
                    listener?.onEditTaskButtonClick(this)

                }


            }
        }


    }


    override fun getItemCount(): Int {

        return list.size


    }


    interface TodoAdapterClicksInterface {
        fun onDeleteTaskButtonClick(toDoData: ToDoData)
        fun onEditTaskButtonClick(toDoData: ToDoData)

    }

}