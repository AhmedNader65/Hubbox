package com.hubbox.model

import com.google.android.gms.maps.model.LatLng


data class Address(
    var ID: String,
    var address: String,
    var country: String,
    var details: String,
    var latLng: LatLng,
    var type: String,
) {
}