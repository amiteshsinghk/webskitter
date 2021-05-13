package com.amitesh.webskittertestproject.ui.fragment.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.databinding.FragmentIntroThirdBinding
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardActivity
import com.amitesh.webskittertestproject.utility.gotToActivityWithoutStack

class IntroThirdFragment : Fragment() {
    private lateinit var binding:FragmentIntroThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_intro_third, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_third, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.letsGo.setOnClickListener {
            requireContext().gotToActivityWithoutStack(DashboardActivity::class.java)
        }
    }
}