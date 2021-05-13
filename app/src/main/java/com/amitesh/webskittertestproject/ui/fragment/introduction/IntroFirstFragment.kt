package com.amitesh.webskittertestproject.ui.fragment.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.prefrences.PreferenceProvider
import com.amitesh.webskittertestproject.databinding.FragmentIntroFirstBinding
import com.amitesh.webskittertestproject.data.preference.PreferenceProvider.SKIPPED
import com.amitesh.webskittertestproject.data.preference.PreferenceProvider.set
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardActivity
import com.amitesh.webskittertestproject.utility.gotToActivityWithoutStack

class IntroFirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentIntroFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_intro_first, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_first, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListner()
    }

    private fun clickListner() {
        binding.skipped.setOnClickListener {
            PreferenceProvider.defaultPrefs(requireContext()).set(SKIPPED, true)
            requireContext().gotToActivityWithoutStack(DashboardActivity::class.java)
        }
    }
}