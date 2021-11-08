package com.hubbox.ui.home.paymentCards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hubbox.R
import com.hubbox.adapters.CardHistoryAdapter
import com.hubbox.model.Spot
import com.hubbox.utils.OrderStatus
import kotlinx.android.synthetic.main.fragment_my_wallet.*


class MyWalletFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topup.setOnClickListener({
            Navigation.findNavController(it).navigate(R.id.nav_send_money)
        })
        manage.setOnClickListener({
            Navigation.findNavController(it).navigate(R.id.nav_payment_cards)
        })

        historyRV.adapter = CardHistoryAdapter(createSpots())
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