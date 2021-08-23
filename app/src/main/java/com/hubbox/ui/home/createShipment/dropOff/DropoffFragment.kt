package com.hubbox.ui.home.createShipment.dropOff

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
import com.hubbox.ui.home.createShipment.CreateShipmentFragment


class DropoffFragment : Fragment() {
    private lateinit var navController: NavController
    lateinit var binding: FragmentDropoffAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dropoff_address, container, false)
        val mRootView = binding.getRoot()

        val language: ArrayList<String> = ArrayList()
        language.add("العربية")
        language.add("English")
        binding.sCountry.adapter =
            SpinnerAdapter(requireContext(), R.layout.item_country_dropdown, language)

        binding.confirm.setOnClickListener {

            val parenFragment = parentFragment as NavHostFragment
            val parenFragment2 = parenFragment.parentFragment as CreateShipmentFragment
            val parenFragment3 = parenFragment2.parentFragment as NavHostFragment

//            val navHostFragment =
//                parenFragment.childFragmentManager.findFragmentById(R.id.nav_shipment_fragment) as NavHostFragment
            val navController = parenFragment3.navController
            navController.navigate(R.id.shipmentInfoEntryFragment)
        }
        return mRootView
    }


}