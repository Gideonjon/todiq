package com.example.behrnintern.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.behrnintern.R
import com.example.behrnintern.adapter.TaskAdapter
import com.example.behrnintern.data.TodiqTask
import com.example.behrnintern.databinding.FragmentDashboardBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var toDoItemList: MutableList<TodiqTask>
    private lateinit var authId: String
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance()


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.addTask.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dashboard_to_taskManager)
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("task")


        binding.textView7.visibility = View.INVISIBLE
        binding.imageView5.visibility = View.INVISIBLE

        auth = FirebaseAuth.getInstance()

        getTask()
        init()



        return view

    }

    private fun init() {
        authId = auth.currentUser!!.uid
        databaseReference = Firebase.database.reference.child("Tasks")
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        toDoItemList = mutableListOf()
        taskAdapter = TaskAdapter(toDoItemList)
        /*taskAdapter.setListener(this)*/
        binding.recyclerView.adapter = taskAdapter


    }

    private fun getTask() {
        binding.recyclerView.visibility = View.INVISIBLE
        binding.imageView5.visibility = View.VISIBLE
        binding.textView7.visibility = View.VISIBLE

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())



        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                toDoItemList.clear()
                for (taskSnapshot in snapshot.children) {
                    val todoTask =
                        taskSnapshot.key?.let { TodiqTask(it, taskSnapshot.value.toString()) }

                    if (todoTask != null) {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.imageView5.visibility = View.INVISIBLE
                        binding.textView7.visibility = View.INVISIBLE

                        toDoItemList.add(todoTask)
                    }

                }
                Log.d(TAG, "onDataChange: " + toDoItemList)
                taskAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {


            }


        }


        )
    }


}



