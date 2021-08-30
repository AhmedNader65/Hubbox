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
import com.hubbox.adapters.SpinnerAdapter
import com.hubbox.databinding.*


class EditProfileFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentEditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)
        val mRootView = binding.getRoot()
        val language: ArrayList<String> = ArrayList()
        language.add("العربية")
        language.add("English")
        binding.sCountry.adapter =
            SpinnerAdapter(requireContext(), R.layout.item_country_dropdown, language)
        binding.sNationality.adapter =
            SpinnerAdapter(requireContext(), R.layout.item_country_dropdown, language)
        return mRootView
    }
}