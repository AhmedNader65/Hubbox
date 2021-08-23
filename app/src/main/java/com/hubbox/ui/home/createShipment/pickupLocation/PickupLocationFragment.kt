package com.hubbox.ui.home.createShipment.pickupLocation

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
import com.hubbox.databinding.*


class PickupLocationFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentPickupAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pickup_address, container, false)
        val mRootView = binding.getRoot()
        binding.confirm.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_pickupLocationFragment_to_pickupContactFragment)
        }
        return mRootView
    }


}