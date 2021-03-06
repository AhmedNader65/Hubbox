package com.hubbox.ui.home.myProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hubbox.R
import com.hubbox.databinding.*


class ChangePasswordFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentChangePasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false)
        val mRootView = binding.getRoot()
        return mRootView
    }
}