package com.hubbox.ui.home.myorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hubbox.R
import com.hubbox.adapters.OrderStatusAdapter
import com.hubbox.model.Spot
import com.hubbox.utils.OrderStatus
import kotlinx.android.synthetic.main.bottom_sheet_order_details.*
import kotlinx.android.synthetic.main.fragment_order_details.*


class OrderStatusFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = layoutInflater.inflate(R.layout.fragment_order_details, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderStatusList.adapter = OrderStatusAdapter(createSpots())
        qrcode.setOnClickListener({
            Navigation.findNavController(it).navigate(R.id.nav_qr_code)
        })

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetConst)
        //#3 Listening to State Changes of BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                hideableContent.visibility = when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> VISIBLE
                    BottomSheetBehavior.STATE_COLLAPSED -> GONE
                    else -> GONE
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(bottomSheetConst)
                    constraintSet.connect(
                        R.id.confirm,
                        ConstraintSet.TOP,
                        R.id.hideableContent,
                        ConstraintSet.BOTTOM,
                        16
                    )
                    constraintSet.applyTo(bottomSheetConst)
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                } else {

                    val constraintSet = ConstraintSet()
                    constraintSet.clone(bottomSheetConst)
                    constraintSet.connect(
                        R.id.confirm,
                        ConstraintSet.TOP,
                        R.id.paymentMethod,
                        ConstraintSet.BOTTOM,
                        16
                    )
                    constraintSet.applyTo(bottomSheetConst)
                }

            }
        })

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
        spots.add(
            Spot(
                name = "Bamboo Forest",
                city = "Kyoto",
                url = "https://source.unsplash.com/buF62ewDLcQ/600x800",
                orderStatus = OrderStatus.WAITING
            )
        )
        spots.add(
            Spot(
                name = "Brooklyn Bridge",
                city = "New York",
                url = "https://source.unsplash.com/THozNzxEP3g/600x800",
                orderStatus = OrderStatus.WAITING
            )
        )
        return spots
    }
}