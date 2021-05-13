package com.amitesh.webskittertestproject.ui.fragment.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.databinding.FragmentIntroSecondBinding

class IntroSecondFragment : Fragment() {
private lateinit var binding:FragmentIntroSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_intro_second, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_second, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IntroSecondFragment()
                .apply {

            }
    }
}