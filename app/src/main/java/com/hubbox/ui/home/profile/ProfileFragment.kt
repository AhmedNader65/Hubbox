package com.hubbox.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hubbox.R
import com.hubbox.databinding.FragmentHomeBinding
import com.hubbox.databinding.FragmentParentHomeBinding
import com.hubbox.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val mRootView = binding.getRoot()

        binding.myProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.myProfileFragment)
        }
        return mRootView
    }
}