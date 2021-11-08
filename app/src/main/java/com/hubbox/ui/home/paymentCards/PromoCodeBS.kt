package com.hubbox.ui.home.paymentCards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hubbox.R
import com.hubbox.adapters.AddressAdapter
import com.hubbox.adapters.PickCardAdapter
import com.hubbox.model.Address
import com.hubbox.model.Spot
import com.hubbox.utils.OrderStatus
import kotlinx.android.synthetic.main.bottom_sheet_payment.*
import kotlinx.android.synthetic.main.bottom_sheet_redeem_code.*

class PromoCodeBS():BottomSheetDialogFragment() {
    var promocoded:Boolean=false



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_redeem_code, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addamountcode.setOnClickListener({
            Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
            dismiss()
        })

    }


    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }



}