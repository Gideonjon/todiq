package com.example.behrnintern.autthScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.behrnintern.R
import com.example.behrnintern.databinding.FragmentLoginBinding


class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root




        binding.forgotPassword.setOnClickListener {

            Navigation.findNavController(view)
                .navigate(R.id.action_registrationViewpager_to_forgetPassword)

        }


        return view
    }


}