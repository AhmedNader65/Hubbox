package com.hubbox.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.hubbox.R
import com.hubbox.adapters.SpinnerAdapter
import com.hubbox.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, com.hubbox.R.layout.fragment_login, container, false)
        val mRootView = binding.getRoot()
        val language: ArrayList<String> = ArrayList()
        language.add("العربية")
        language.add("English")
        binding.sCountry.adapter =
            SpinnerAdapter(requireContext(), R.layout.item_country_dropdown, language)
        binding.submitPhone.setOnClickListener {
            var countryCode = binding.countryCode.text
            countryCode = countryCode.subSequence(0, countryCode.length - 1)
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToOTPFragment(("$countryCode ${binding.phoneEntry.text.toString()}")))
        }
        return mRootView
    }
}