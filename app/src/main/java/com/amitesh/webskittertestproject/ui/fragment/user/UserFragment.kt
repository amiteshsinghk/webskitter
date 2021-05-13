package com.amitesh.webskittertestproject.ui.fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.entities.User
import com.amitesh.webskittertestproject.databinding.FragmentUserBinding
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardViewModel
import com.amitesh.webskittertestproject.utility.Validator
import com.amitesh.webskittertestproject.utility.getViewModel
import com.amitesh.webskittertestproject.utility.trycatch
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = requireActivity().getViewModel() { DashboardViewModel() }

        initUI()
        initClick()
    }
    private fun goBack() {
        trycatch {
            requireActivity().findNavController(R.id.hostController).navigateUp()
        }
    }
    private fun initClick() {
        binding.imageView2.setOnClickListener {
            goBack()
        }
        binding.btnSubmit.setOnClickListener {
            if (isEveryFieldValid()) {
                trycatch {
                    if (viewModel.clickedUser == null) {
                        viewModel.add(User(0, binding.tieName.text.toString().trim(), binding.tieEmail.text.toString().trim()))
                    } else {
                        viewModel.clickedUser?.run {
                            viewModel.update(User(this.id, binding.tieName.text.toString().trim(), binding.tieEmail.text.toString().trim()))
                        }
                    }
                    goBack()
                }
            }
        }

    }
    private fun isEveryFieldValid(): Boolean {
        if (!Validator.isTextValid(tilName, tieName, Validator.name)) return false
        if (!Validator.isTextValid(tilEmail, tieEmail, Validator.emailAddress)) return false
        return true
    }
    private fun initUI() {
        trycatch {
            viewModel.clickedUser?.run {
                binding.tieName.setText(name)
                binding.tieEmail.setText(email)
            }
        }
    }
}