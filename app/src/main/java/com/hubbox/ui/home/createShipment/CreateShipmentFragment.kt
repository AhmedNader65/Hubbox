package com.hubbox.ui.home.createShipment

import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.hubbox.R
import com.hubbox.adapters.AddressAdapter
import com.hubbox.model.Address
import kotlinx.android.synthetic.main.fragment_new_shipment.*


class CreateShipmentFragment : Fragment(), AddressAdapter.OnAddressInteract {
    private lateinit var placesClient: PlacesClient
    private lateinit var gMap: GoogleMap
    private lateinit var vm: ShipmentMainVM
    private val addressesList: MutableList<Address> = mutableListOf()

    private var mResult: StringBuilder = StringBuilder()
    private val TAG: String = "Create Shipment"
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    private lateinit var navController: NavController

    // Set the fields to specify which types of place data to
    // return after the user has made a selection.
    val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)
    private val callback = OnMapReadyCallback { googleMap ->
        this.gMap = googleMap
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(), R.raw.map_style
                )
            )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
        val sydney = LatLng(-34.0, 151.0)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = layoutInflater.inflate(R.layout.fragment_new_shipment, null)
        vm = ViewModelProvider(this).get(ShipmentMainVM::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_shipment_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // Initialize the AutocompleteSupportFragment.

        // Create a new Places client instance.
        placesClient = Places.createClient(requireContext())
        placeEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val token = AutocompleteSessionToken.newInstance()
                val curScreen: LatLngBounds = gMap.getProjection()
                    .getVisibleRegion().latLngBounds
                val bounds = RectangularBounds.newInstance( //dummy lat/lng
                    curScreen.southwest,
                    curScreen.northeast
                )
                // Use the builder to create a FindAutocompletePredictionsRequest.
                // Use the builder to create a FindAutocompletePredictionsRequest.
                val request =
                    FindAutocompletePredictionsRequest.builder() // Call either setLocationBias() OR setLocationRestriction().
                        .setLocationBias(bounds) //.setLocationRestriction(bounds)
//                        .setCountry("ng") //Nigeria
//                        .setTypeFilter(TypeFilter.ADDRESS)
                        .setSessionToken(token)
                        .setQuery(s.toString())
                        .build()
                try {
                    placesClient.findAutocompletePredictions(request).addOnCompleteListener {
                        mResult = StringBuilder()
                        addressesList.clear()
                        for (prediction in it.result.getAutocompletePredictions()) {
                            addressesList.add(
                                Address(
                                    prediction.placeId,
                                    prediction.getPrimaryText(null).toString(),
                                    prediction.getSecondaryText(null).toString(),
                                    "",
                                    LatLng(0.0,0.0),
                                    ""
                                )
                            )
                            mResult!!.append(" ")
                                .append(prediction.getFullText(null).toString() + "\n")
                            Log.i(TAG, prediction.placeId)
                            Log.i(TAG, prediction.getPrimaryText(null).toString())
                            Log.i(TAG, prediction.getSecondaryText(null).toString())
                        }
                        if (addressesList.size > 0) {
                            suggestionsCard.visibility = VISIBLE
                            placesList.adapter =
                                AddressAdapter(addressesList, this@CreateShipmentFragment)
                        } else {
                            suggestionsCard.visibility = GONE
                            placesList.adapter = null
                        }
                        Log.e("Result : ", mResult.toString())
                    }.addOnFailureListener {
                        val apiException = it as ApiException
                        Log.e(TAG, "Place not found: " + apiException.statusCode)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onAddressChoosen(address: Address) {
        val placeId = address.ID

        suggestionsCard.visibility = GONE
        addressesList.clear()
        // Specify the fields to return.
        val placeFields =
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)

        // Construct a request object, passing the place ID and fields array.
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener { response: FetchPlaceResponse ->
                val place = response.place
                address.address = place.address!!
                address.latLng = place.latLng!!
                vm.pickupAddress.postValue(address)
                val location = address.latLng
                gMap.addMarker(MarkerOptions().position(location).title(place.name).icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_new_marker)))
                gMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        location, 12.0f
                    )
                )
                Log.i("PlaceDetailsActiv", "Place found: ${place.name} address${place.address}")
            }.addOnFailureListener { exception: Exception ->
                if (exception is ApiException) {
                    Log.e(TAG, "Place not found: ${exception.message}")
                    val statusCode = exception.statusCode
                    TODO("Handle error with given status code")
                }
            }
    }

}