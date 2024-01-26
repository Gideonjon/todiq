package com.example.behrnintern

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.behrnintern.databinding.FragmentForgetPasswordBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ForgetPassword : BottomSheetDialogFragment() {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fpsw.setOnClickListener {
            Toast.makeText(requireContext(), "Password Sent", Toast.LENGTH_SHORT).show()
            dismiss()

        }


        return view
    }


}