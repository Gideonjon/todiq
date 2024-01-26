package com.example.behrnintern.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.behrnintern.databinding.FragmentRegistrationViewpagerBinding
import com.example.foodmart.registration.RegistrationAdapter
import com.google.android.material.tabs.TabLayout


class RegistrationViewpager : Fragment() {
    private var _binding: FragmentRegistrationViewpagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RegistrationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentRegistrationViewpagerBinding.inflate(inflater, container, false)
        val view = binding.root


        adapter = RegistrationAdapter(requireActivity().supportFragmentManager, lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Login"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Register"))


        binding.viewPager.adapter = adapter


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }


        })





        return view
    }

}