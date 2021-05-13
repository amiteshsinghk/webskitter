package com.amitesh.webskittertestproject.ui.fragment.user_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.databinding.FragmentUserBinding
import com.amitesh.webskittertestproject.databinding.FragmentUserListBinding
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardViewModel
import com.amitesh.webskittertestproject.utility.getViewModel
import com.google.android.gms.maps.model.Dash


class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var viewModel: DashboardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user_list, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_list,container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = requireActivity().getViewModel() { DashboardViewModel() }

        initUI()
    }

    private fun initUI() {
        binding.rvUsers.adapter = viewModel.userAdapter
        binding.fabAdd.setOnClickListener {
            requireActivity().findNavController(R.id.hostController).navigate(R.id.userFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.clickedUser = null
        viewModel.fetchUsers()
    }


}