package com.example.behrnintern.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.behrnintern.databinding.FragmentTaskManagerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TaskManager : Fragment() {
    private var _binding: FragmentTaskManagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()


        _binding = FragmentTaskManagerBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.imageTick.visibility = View.INVISIBLE

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable?) {

                if (binding.titleEt.text.toString()
                        .isEmpty() && binding.descriptionEt.text.toString().isEmpty()
                ) {
                    binding.imageTick.visibility = View.INVISIBLE
                } else {
                    binding.imageTick.visibility = View.VISIBLE
                }


            }


        }

        binding.titleEt.addTextChangedListener(textWatcher)
        binding.descriptionEt.addTextChangedListener(textWatcher)


        binding.imageTick.setOnClickListener {
            val title = binding.titleEt.text.toString()
            val description = binding.descriptionEt.text.toString()
            saveToFirebase(title, description)
        }



        return view
    }

    private fun saveToFirebase(title: String, description: String) {
        val userId = System.currentTimeMillis().toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("task").child(userId)
        val userMap = mapOf("title" to title, "description" to description)
        databaseReference.setValue(userMap)


    }


}