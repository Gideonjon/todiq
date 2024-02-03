package com.example.behrnintern.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.behrnintern.databinding.FragmentTaskManagerBinding
import com.example.kotlintodopractice.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText


class TaskManager : DialogFragment() {
    private var _binding: FragmentTaskManagerBinding? = null
    private val binding get() = _binding!!
    private var listener: OnDialogNextBtnClickListener? = null
    private var toDoData: ToDoData? = null


    fun setListener(listener: dashboard) {
        this.listener = listener
    }

    companion object {
        const val TAG = "DialogFragment"

        @JvmStatic
        fun newInstance(taskId: String, title: String, description: String) =
            TaskManager().apply {
                arguments = Bundle().apply {
                    putString("taskId", taskId)
                    putString("title", title)
                    putString("description", description)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment


        _binding = FragmentTaskManagerBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {

            toDoData = ToDoData(
                arguments?.getString("taskId").toString(),
                arguments?.getString("title").toString(),
                arguments?.getString("description").toString()
            )
            binding.titleEt.setText(toDoData?.title)
            binding.descriptionEt.setText(toDoData?.description)
        }


        binding.todoClose.setOnClickListener {
            dismiss()
        }

        binding.todoNextBtn.setOnClickListener {

            val todoTitle = binding.titleEt.text.toString()
            val todoDescription = binding.descriptionEt.text.toString()
            if (todoTitle.isNotEmpty()) {
                if (toDoData == null) {
                    listener?.saveTask(
                        todoTitle,
                        binding.titleEt,
                        todoDescription,
                        binding.descriptionEt
                    )
                } else {
                    toDoData!!.title = todoTitle
                    toDoData!!.description = todoDescription
                    listener?.updateTask(
                        toDoData!!,
                        binding.titleEt,
                        todoDescription,
                        binding.descriptionEt
                    )
                }

            }
        }
    }

    interface OnDialogNextBtnClickListener {
        fun saveTask(
            todoTitle: String,
            todoEdit: TextInputEditText,
            todoDescription: String,
            todoDescriptionEt: TextInputEditText
        )

        fun updateTask(
            toDoData: ToDoData,
            todoEdit: TextInputEditText,
            todoDescription: String,
            todoDescriptionEt: TextInputEditText
        )
    }

}


