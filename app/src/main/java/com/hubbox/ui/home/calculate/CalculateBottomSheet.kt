package com.hubbox.ui.home.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hubbox.R
import com.hubbox.adapters.FAQAdapter
import com.hubbox.adapters.TodayShippingRatesAdapter
import com.hubbox.model.FAQ
import com.hubbox.model.ShippingRate
import kotlinx.android.synthetic.main.bottom_sheet_shipping_rates.*
import kotlinx.android.synthetic.main.fragment_help_support.*

class CalculateBottomSheet(val mListener: OnCalculateClicked): BottomSheetDialogFragment() {
    lateinit var example: ArrayList<ShippingRate>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_shipping_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculateagain.setOnClickListener({
            mListener.onCalculateClickde()
        })
        example= ArrayList()
        example.add(ShippingRate("a"))
        example.add(ShippingRate("a"))
        shipmentsrv.adapter= TodayShippingRatesAdapter(example)

    }

    interface OnCalculateClicked {
        fun onCalculateClickde()
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }


}

