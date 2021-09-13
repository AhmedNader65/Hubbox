package com.hubbox.ui.home.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import com.hubbox.BuildConfig
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
        binding.paymentCards.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.paymentCardsFragment)
        }
        binding.preferences.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.refundPreferencesFragment)
        }
        binding.shareApp.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.sharingText)
                    +" https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
            intent.setType("text/plain")
            startActivity(intent)
        }
        binding.shareApp.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.sharingText)
                    +" https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
            intent.setType("text/plain")
            startActivity(intent)
        }
        binding.rateApp.setOnClickListener {
            val uri = Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
            or Intent.FLAG_ACTIVITY_NEW_TASK)
            try {
                startActivity(intent)
            }catch (e: ActivityNotFoundException){
                startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")))
            }
        }
        return mRootView
    }
}