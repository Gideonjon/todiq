package com.example.behrnintern.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.behrnintern.adapter.TaskAdapter
import com.example.behrnintern.data.User
import com.example.behrnintern.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var taskArrayList: ArrayList<User>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance()


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        databaseReference = FirebaseDatabase.getInstance().reference.child("task")
        auth = FirebaseAuth.getInstance()

        taskArrayList = arrayListOf<User>()
        getTask()



        return view

    }

    private fun getTask() {

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    binding.textView7.visibility = View.INVISIBLE
                    binding.imageView5.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE


                    for (userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(User::class.java)
                        taskArrayList.add(user!!)

                    }
                    binding.textView7.visibility = View.INVISIBLE
                    binding.imageView5.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = TaskAdapter(taskArrayList)

                }


            }

            override fun onCancelled(error: DatabaseError) {


            }


        })


    }


}


