package com.hubbox.ui.home.createShipment.pickupContact

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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hubbox.R
import com.hubbox.adapters.SpinnerAdapter
import com.hubbox.databinding.*


class PickupContactFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentPickupContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pickup_contact, container, false)
        val mRootView = binding.getRoot()

        val language: ArrayList<String> = ArrayList()
        language.add("العربية")
        language.add("English")
        binding.sCountry.adapter =
            SpinnerAdapter(requireContext(), R.layout.item_country_dropdown, language)

        binding.confirm.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_pickupContactFragment_to_dropoffFragment)
        }
        return mRootView
    }


}