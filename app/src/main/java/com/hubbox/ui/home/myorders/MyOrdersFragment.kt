package com.hubbox.ui.home.myorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.hubbox.R
import com.hubbox.databinding.FragmentMyOrdersBinding


class MyOrdersFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentMyOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_orders, container, false)
        val mRootView = binding.getRoot()

        return mRootView
    }
}