package com.hubbox.ui.home.createShipment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hubbox.model.Address

class ShipmentMainVM : ViewModel() {
    val pickupAddress : MutableLiveData<Address> = MutableLiveData()

}