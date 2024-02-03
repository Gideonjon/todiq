package com.example.behrnintern.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.behrnintern.R
import com.example.behrnintern.data.TodiqTask
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

        databaseReference = FirebaseDatabase.getInstance().reference.child("task")
        auth = FirebaseAuth.getInstance()

        val uid = auth.currentUser?.uid



        _binding = FragmentTaskManagerBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.iconBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_taskManager_to_dashboard)
        }

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
            val users = TodiqTask(
                binding.titleEt.text.toString(), binding.descriptionEt.text.toString()

            )
            if (uid != null) {
                databaseReference.child(uid).setValue(users).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "Task Created", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {


                    Toast.makeText(requireContext(), "Task Not Created", Toast.LENGTH_SHORT).show()
                }
            }

        }





        return view
    }


}