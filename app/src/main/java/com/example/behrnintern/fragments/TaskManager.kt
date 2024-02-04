package com.example.behrnintern.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.behrnintern.databinding.FragmentTaskManagerBinding
import com.example.kotlintodopractice.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText


class TaskManager : DialogFragment() {

    private var _binding: FragmentTaskManagerBinding? = null
    private val binding get() = _binding!!
    private var listener: OnDialogNextBtnClickListener? = null


    fun setListener(listener: OnDialogNextBtnClickListener) {
        this.listener = listener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment


        _binding = FragmentTaskManagerBinding.inflate(inflater, container, false)
        val view = binding.root




        binding.todoClose.setOnClickListener {
            dismiss()
        }

        binding.todoNextBtn.setOnClickListener {

            val todoTitle = binding.titleEt.text.toString()
            val todoDescription = binding.descriptionEt.text.toString()
            val todoTask = ToDoData(todoTitle, todoDescription)
            if (binding.titleEt.text.toString().isEmpty() && binding.descriptionEt.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(requireContext(), "Fields Cant Be Empty", Toast.LENGTH_SHORT).show()
            } else {
                listener?.saveTask(todoTask, binding.titleEt, binding.descriptionEt)
            }
        }

        return view
    }
}

interface OnDialogNextBtnClickListener {
    fun saveTask(
        dialer: ToDoData,
        todoTitle: TextInputEditText,
        todoDescriptionEt: TextInputEditText
    )

    /*   fun updateTask(
           dialer: ToDoData,
           todoTitle: TextInputEditText,
           todoDescription: TextInputEditText
       )*/


}




