package com.example.behrnintern.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.behrnintern.adapter.TodoAdapter
import com.example.behrnintern.databinding.FragmentDashboardBinding
import com.example.kotlintodopractice.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class dashboard : Fragment(), OnDialogNextBtnClickListener {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var popUpFragment: TaskManager
    private lateinit var userArrayList: MutableList<ToDoData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root


        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("task")

        binding.addTask.setOnClickListener {
            popUpFragment = TaskManager()
            popUpFragment.setListener(this)
            popUpFragment.show(childFragmentManager, "task")
        }

        userArrayList = arrayListOf<ToDoData>()
        getUserData()

        return view

    }

    private fun getUserData() {


        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {


                    for (userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(ToDoData::class.java)
                        userArrayList.add(user!!)

                    }
                    binding.recyclerView.adapter = TodoAdapter(userArrayList)

                }


            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }


    override fun saveTask(
        dialer: ToDoData,
        todoTitle: TextInputEditText,
        todoDescriptionEt: TextInputEditText
    ) {

        databaseReference.push().setValue(dialer).addOnCompleteListener {

            if (it.isSuccessful) {
                Toast.makeText(context, "Task Created Successfully", Toast.LENGTH_SHORT)
                    .show()

            } else {
                Toast.makeText(context, "Task not created", Toast.LENGTH_SHORT).show()
            }

            popUpFragment.dismiss()

        }
    }

    /* override fun updateTask(
         dialer: ToDoData,
         todoTitle: TextInputEditText,
         todoDescription: TextInputEditText
     ) {
         TODO("Not yet implemented")
     }*/


}



