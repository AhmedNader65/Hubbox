package com.hubbox.ui.home.profile.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.gms.maps.model.LatLng
import com.hubbox.R
import com.hubbox.adapters.HomeAddressAdapter
import com.hubbox.model.HomeAddress
import kotlinx.android.synthetic.main.fragment_location_info.*
import kotlinx.android.synthetic.main.fragment_saved_location.*

class SavedLocationFragment : Fragment(), HomeAddressAdapter.OnAddressInteract {

    lateinit var homes: java.util.ArrayList<HomeAddress>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homes= ArrayList()
       homes.add(HomeAddress("","","","", LatLng(1.11,1.11),""))
        homerv.adapter=HomeAddressAdapter(homes,this)
        work_rv.adapter=HomeAddressAdapter(homes,this)
        addLocation.setOnClickListener({
            Navigation.findNavController(it).navigate(R.id.nav_add_loca_map)
        })

    }

    override fun onAddressChoosen(address: HomeAddress) {


    }
}