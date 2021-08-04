package com.hubbox.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.hubbox.R
import com.hubbox.databinding.FragmentOtpBinding


class OTPFragment : Fragment() {
    val args: OTPFragmentArgs by navArgs()

    lateinit var binding: FragmentOtpBinding
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding =
            DataBindingUtil.inflate(inflater, com.hubbox.R.layout.fragment_otp, container, false)
        val mRootView = binding.getRoot()
        var phone = args.phone
        phone = phone.replace(" ", "")
        removePhoneKeypad(mRootView)
        binding.phone.text = getString(R.string.phone_number) + " " + phone
        binding.keypad.setInputConnection(binding.otp.onCreateInputConnection(EditorInfo()))

        binding.verify.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_OTPFragment_to_homeFragment)
        }
        return mRootView
    }

    fun removePhoneKeypad(view: View) {
        val inputManager = view
            .getContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val binder = view.windowToken
        inputManager.hideSoftInputFromWindow(
            binder,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
}