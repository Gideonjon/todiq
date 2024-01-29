package com.example.behrnintern.authScreens

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.behrnintern.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class Register : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        binding.progressBar.visibility = View.INVISIBLE

        binding.login.setOnClickListener {

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()) {
                binding.email.error = "Wrong Email"
                binding.email.requestFocus()
            }

            if (binding.passwordEt.text.toString().isEmpty()) {
                binding.password.error = "Input Your Password"
                binding.password.requestFocus()
            } else {
                auth.createUserWithEmailAndPassword(
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Successfully Completed",
                                Toast.LENGTH_SHORT
                            ).show()


                            binding.progressBar.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(
                                requireContext(), "Account Not Created" +
                                        "", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }


            }


        }




        return view
    }


}