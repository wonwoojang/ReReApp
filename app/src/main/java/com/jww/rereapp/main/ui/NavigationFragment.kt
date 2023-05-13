package com.jww.rereapp.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentNavigationBinding
import com.jww.rereapp.databinding.ItemNavigationTabBinding
import com.jww.rereapp.enums.NavigationType
import com.jww.rereapp.main.info.ui.InfoFragment
import com.jww.rereapp.main.movie.ui.MovieFragment
import com.jww.rereapp.main.rere.ui.ReReFragment
import com.jww.rereapp.main.webToon.ui.WebToonFragment

class NavigationFragment : BaseFragment() {

    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    private val onPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Log.d("Jww", "현재 위치 ${NavigationType.getType(position)}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.run {
            rootContainer.adapter = NavigationAdapter()
            rootContainer.registerOnPageChangeCallback(onPageChangeListener)

            TabLayoutMediator(tabLayout, rootContainer, true, false) { tab, position ->
                tab.customView = addTab(tab, position)
            }.attach()
        }
    }

    private fun addTab(tab: TabLayout.Tab, position: Int): View {
        return ItemNavigationTabBinding.inflate(layoutInflater, tab.view, false).apply {
            when (NavigationType.getType(position)) {
                NavigationType.RERE -> {
                    this.name.text = NavigationType.getType(position).value
                }
                NavigationType.MOVIE -> {
                    this.name.text = NavigationType.getType(position).value
                }
                NavigationType.WEBTOON -> {
                    this.name.text = NavigationType.getType(position).value
                }
                NavigationType.INFO -> {
                    this.name.text = NavigationType.getType(position).value
                }
            }

        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rootContainer.unregisterOnPageChangeCallback(onPageChangeListener)
    }

    inner class NavigationAdapter : FragmentStateAdapter(this) {
        override fun getItemCount() = NavigationType.values().size

        override fun createFragment(position: Int): Fragment {
            return when (NavigationType.getType(position)) {
                NavigationType.RERE -> ReReFragment()
                NavigationType.MOVIE -> MovieFragment()
                NavigationType.WEBTOON -> WebToonFragment()
                NavigationType.INFO -> InfoFragment()
            }
        }
    }
}