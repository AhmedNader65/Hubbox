package com.hubbox.ui.home.calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.card.MaterialCardView
import com.hubbox.R
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import kotlinx.android.synthetic.main.fragment_calculate_rates.*
import kotlinx.android.synthetic.main.fragment_calculate_rates.recovery

class CalculateRatesFragment : Fragment(), CalculateBottomSheet.OnCalculateClicked {

    lateinit var calculateBottomSheet: CalculateBottomSheet
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        document.setOnClickListener({shipmentTypeCard(document,R.color.delivery_Tertiarry)})
        parcell.setOnClickListener({shipmentTypeCard(parcell,R.color.purple)})
        recovery.setOnClickListener({shipmentTypeCard(recovery,R.color.fushia)})
        Moving.setOnClickListener({shipmentTypeCard(Moving,R.color.orange)})

        Expresslin.setOnClickListener({
            deliveryType(Expresslin,exptitle,expdesc)
        })

        Standardlin.setOnClickListener({
            deliveryType(Standardlin,standardtxt,standarddesc)
        })

        Customizelin.setOnClickListener({
            deliveryType(Customizelin,customizetxt,customizedesc)
        })

        CalculateRates.setOnClickListener({
            calculateBottomSheet= CalculateBottomSheet(this)
            calculateBottomSheet.show(parentFragmentManager,"TAG")

        })


    }

    fun shipmentTypeCard(materialCardView: MaterialCardView,color:Int){
        document.setStrokeColor(resources.getColor(R.color.greenlight))
        parcell.setStrokeColor(resources.getColor(R.color.purplelight))
        Moving.setStrokeColor(resources.getColor(R.color.orangelight))
        recovery.setStrokeColor(resources.getColor(R.color.pinklight))
        materialCardView.setStrokeColor(resources.getColor(color))
        materialCardView.strokeWidth=2
    }

    fun deliveryType(linearLayout: LinearLayout,header:TextView,desc:TextView){
        Expresslin.background=resources.getDrawable(R.drawable.bg_white_with_stroke)
        Standardlin.background=resources.getDrawable(R.drawable.bg_white_with_stroke)
        Customizelin.background=resources.getDrawable(R.drawable.bg_white_with_stroke)
        expdesc.setTextColor(resources.getColor(R.color.delivery_black))
        exptitle.setTextColor(resources.getColor(R.color.delivery_black))
        standarddesc.setTextColor(resources.getColor(R.color.delivery_black))
        standardtxt.setTextColor(resources.getColor(R.color.delivery_black))
        customizetxt.setTextColor(resources.getColor(R.color.delivery_black))
        customizedesc.setTextColor(resources.getColor(R.color.delivery_black))


        linearLayout.background=resources.getDrawable(R.drawable.bg_round_purple_small_rad)
        header.setTextColor(resources.getColor(R.color.white))
        desc.setTextColor(resources.getColor(R.color.white))
    }

    override fun onCalculateClickde() {
        calculateBottomSheet.dismiss()
        Navigation.findNavController(Expresslin).navigate(R.id.nav_calculate_rate)

    }
}