package com.example.nestedrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nestedrecyclerview.adapter.ViewPager2Adapter
import com.example.nestedrecyclerview.databinding.ActivityMainBinding
import com.example.nestedrecyclerview.ext.allotEachTabWithEqualWidth
import com.example.nestedrecyclerview.ext.setupWithViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragments: ArrayList<Fragment> by lazy {
        arrayListOf(
            OneFragment.newInstance(),
            TwoFragment.newInstance()
        )
    }
    private val titles by lazy {
        arrayListOf(
            "One",
            "Two"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val pagerAdapter = ViewPager2Adapter(supportFragmentManager, lifecycle)
        pagerAdapter.addFragments(fragments)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.offscreenPageLimit = fragments.size - 1
        binding.tabLayout.setupWithViewPager2(binding.viewPager, titles)
        binding.tabLayout.allotEachTabWithEqualWidth()
    }
}
