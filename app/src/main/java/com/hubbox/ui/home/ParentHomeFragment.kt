package com.hubbox.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hubbox.R
import com.hubbox.databinding.FragmentParentHomeBinding


class ParentHomeFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentParentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_home, container, false)
        val mRootView = binding.getRoot()
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_home_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.botttomNavigation.visibility = VISIBLE
            when (destination.id) {
                R.id.navigation_home -> binding.root.setBackgroundResource(R.color.lavendar)
                R.id.navigation_my_orders -> binding.root.setBackgroundResource(R.color.my_orders_bg)
                R.id.navigation_profile -> binding.root.setBackgroundResource(R.color.white)
                else -> binding.botttomNavigation.visibility = GONE
            }
        }
        NavigationUI.setupWithNavController(binding.botttomNavigation, navController)
        return mRootView
    }
}