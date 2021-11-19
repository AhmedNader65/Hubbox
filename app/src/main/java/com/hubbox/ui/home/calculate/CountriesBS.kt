package com.hubbox.ui.home.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hubbox.R
import com.hubbox.adapters.CountryAdapter
import com.hubbox.adapters.FAQAdapter
import com.hubbox.adapters.TodayShippingRatesAdapter
import com.hubbox.model.Country
import com.hubbox.model.FAQ
import com.hubbox.model.ShippingRate
import kotlinx.android.synthetic.main.bottom_sheet_countriest.*
import kotlinx.android.synthetic.main.bottom_sheet_shipping_rates.*
import kotlinx.android.synthetic.main.fragment_help_support.*

class CountriesBS(val countries:List<Country>,val mListener: OnCountrySelected,val type:Int): BottomSheetDialogFragment(),
    CountryAdapter.OnCountryInteracted {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_countriest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardsrv.adapter=CountryAdapter(countries,this)

    }

    interface OnCountrySelected {
        fun onCountrySelected( country: Country,type: Int)
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }

    override fun onCountryChoosen(country: Country) {
        mListener.onCountrySelected(country,type)
        dismiss()
    }


}

