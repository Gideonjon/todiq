package com.example.behrnintern

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.behrnintern.databinding.FragmentForgetPasswordBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth


class ForgetPassword : BottomSheetDialogFragment() {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()

        binding.fpsw.setOnClickListener {
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.fpswEt.text.toString()).matches()) {
                binding.emailFpsw.error = "Wrong Email"
                binding.emailFpsw.requestFocus()
            } else {
                auth.sendPasswordResetEmail(binding.fpswEt.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Check Your Email For Instructions",
                                Toast.LENGTH_SHORT
                            ).show()
                            dismiss()
                        } else {
                            binding.fpswEt.setText("")
                            Toast.makeText(
                                requireContext(),
                                "Reset Email Not Seen",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }


        return view
    }


}