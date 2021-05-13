package com.amitesh.webskittertestproject.ui.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.databinding.ActivityIntroductionBinding
import com.amitesh.webskittertestproject.ui.fragment.introduction.IntroFirstFragment
import com.amitesh.webskittertestproject.ui.fragment.introduction.IntroSecondFragment
import com.amitesh.webskittertestproject.ui.fragment.introduction.IntroThirdFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import com.amitesh.webskittertestproject.data.preference.PreferenceProvider
import com.amitesh.webskittertestproject.data.preference.PreferenceProvider.get
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardActivity
import com.amitesh.webskittertestproject.utility.gotToActivityWithoutStack
import kotlinx.coroutines.launch

class IntroductionActivity : AppCompatActivity() {
        private lateinit var binding :ActivityIntroductionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_introduction)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_introduction)
        binding.lifecycleOwner = this
        checkSkipped()
        initViewPager()
    }

    private fun checkSkipped() {
        lifecycleScope.launch {
            val data: Boolean? = PreferenceProvider.defaultPrefs(this@IntroductionActivity).get(PreferenceProvider.SKIPPED)
            if (data != null && data)
                gotToActivityWithoutStack(DashboardActivity::class.java)
        }
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
        }
    }

    private fun initViewPager() {
        val fragmentList = arrayListOf(
            IntroFirstFragment(),
            IntroSecondFragment(),
            IntroThirdFragment()
        )
        binding.viewPager.adapter = ViewPagerAdapter(this, fragmentList)
        binding.tabLayoutIntro.setupWithViewPager(binding.viewPager,fragmentList)
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity, val fragmentList: ArrayList<Fragment>) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentList.size
        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}

private fun TabLayout.setupWithViewPager(viewPager: ViewPager2, fragmentList: java.util.ArrayList<Fragment>) {
    if (fragmentList.size != viewPager.adapter?.itemCount)
        throw Exception("The size of list and the tab count should be equal!")
    TabLayoutMediator(this, viewPager,
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        }).attach()
}