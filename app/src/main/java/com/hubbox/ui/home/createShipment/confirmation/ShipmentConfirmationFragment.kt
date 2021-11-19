package com.hubbox.ui.home.createShipment.confirmation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
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
import com.google.android.material.card.MaterialCardView
import com.hubbox.R
import com.hubbox.adapters.DayAdapter
import com.hubbox.adapters.SpinnerAdapter
import com.hubbox.adapters.VehicleAdapter
import com.hubbox.databinding.*
import com.hubbox.model.Day
import com.hubbox.model.Spot
import com.hubbox.model.Vehicle
import android.text.Editable

import android.text.TextWatcher





class ShipmentConfirmationFragment : Fragment() {
    lateinit var binding: FragmentShipmentConfirmationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shipment_confirmation,
                container,
                false
            )
        val mRootView = binding.getRoot()

        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.plusimg.setOnClickListener({
            binding.addresscon.visibility = View.VISIBLE
            binding.plusimg.visibility=View.GONE
            binding.minusimg.visibility=View.VISIBLE
        })
        binding.minusimg.setOnClickListener({
            binding.addresscon.visibility = View.GONE
            binding.plusimg.visibility=View.VISIBLE
            binding.minusimg.visibility=View.GONE
        })


        binding.promocode.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.applyPromo.isEnabled=true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
               binding.applyPromo.isEnabled=true
            }
        })
    }
}