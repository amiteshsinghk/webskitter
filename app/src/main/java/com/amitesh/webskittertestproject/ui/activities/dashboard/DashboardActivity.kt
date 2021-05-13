package com.amitesh.webskittertestproject.ui.activities.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.db.DatabaseBuilder
import com.amitesh.webskittertestproject.data.db.DatabaseHelperImpl
import com.amitesh.webskittertestproject.databinding.ActivityDashboardBinding
import com.amitesh.webskittertestproject.utility.getViewModel
import kotlin.math.log

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    var TAG="1111"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        viewModel = getViewModel() { DashboardViewModel() }
        binding.lifecycleOwner = this
        viewModel.dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(this))
        viewModel.hostController = findNavController(R.id.hostController)

        setBottomNavigation()
    }
    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { itemMenu ->
            when (itemMenu.toString()) {
              "Home" -> {
                    clearBackStack()
                    Log.d("TishaData", "albumCLICKED")
                    viewModel.hostController?.navigate(R.id.albumFragment)
                }
                "Map" -> {
                    clearBackStack()
                    Log.d("TishaData", "mapsFragment")
                    viewModel.hostController?.navigate(R.id.mapsFragment)
                }
                "Profile" -> {
                    clearBackStack()
                    Log.d("TishaData", "mapsFragment")
                    viewModel.hostController?.navigate(R.id.userListFragment)
                }
            }
            true
        }
    }
    private fun clearBackStack() {
        viewModel.hostController?.popBackStack(R.id.albumFragment, true)
        viewModel.hostController?.popBackStack(R.id.mapsFragment, true)
        viewModel.hostController?.popBackStack(R.id.userFragment, true)
    }
}