package com.hubbox.ui.home.paymentCards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hubbox.R
import com.hubbox.adapters.AddressAdapter
import com.hubbox.adapters.PickCardAdapter
import com.hubbox.model.Address
import com.hubbox.model.Spot
import com.hubbox.utils.OrderStatus
import kotlinx.android.synthetic.main.bottom_sheet_payment.*

class PickCardBS(val mListener: OnCardPicked):BottomSheetDialogFragment() {
    var promocoded:Boolean=false



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_payment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardsrv.adapter=PickCardAdapter(createSpots())
        prompcode.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {promocoded=true}
            else promocoded=false
        }

        addamount.setOnClickListener({
            if (promocoded){
                mListener.onPromoCodeClicked()
                dismiss()
            }
            else{
                mListener.onCardClicked()
                dismiss()
            }
        })
        addnewcard.setOnClickListener({
            mListener.OnAddCard()
            dismiss()
        })
    }


    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }

    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()
        spots.add(
            Spot(
                name = "Yasaka Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                orderStatus = OrderStatus.WAITING
            )
        )
        spots.add(
            Spot(
                name = "Fushimi Inari Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/NYyCqdBOKwc/600x800",
                orderStatus = OrderStatus.WAITING
            )
        )
        return spots
    }

    interface OnCardPicked {
        fun onCardClicked()
        fun onPromoCodeClicked()
        fun OnAddCard()
    }
}