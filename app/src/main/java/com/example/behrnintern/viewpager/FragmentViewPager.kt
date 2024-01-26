package com.example.behrnintern.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.behrnintern.R
import com.example.behrnintern.databinding.FragmentViewPagerBinding
import com.example.behrnintern.onboardingScreen.FirstScreen
import com.example.behrnintern.onboardingScreen.SecondScreen
import com.example.behrnintern.onboardingScreen.ThirdScreen
import com.example.foodmart.viewpager.onboarding


class FragmentViewPager : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        var fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )


        val adapter = onboarding(
            fragmentList,
            requireActivity().supportFragmentManager, lifecycle
        )
        binding.viewPager.adapter = adapter


        binding.btnGetStarted.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_fragmentViewPager_to_registrationViewpager)

        }






        return view

    }

}