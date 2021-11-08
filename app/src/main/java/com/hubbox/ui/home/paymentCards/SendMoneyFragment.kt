package com.hubbox.ui.home.paymentCards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.hubbox.R
import kotlinx.android.synthetic.main.fragment_send_money.*


class SendMoneyFragment : Fragment(), PickCardBS.OnCardPicked {
lateinit var pickCardBS: PickCardBS
lateinit var promoCodeBS: PromoCodeBS

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_money, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmsend.setOnClickListener({
            pickCardBS= PickCardBS(this)
            pickCardBS.show(childFragmentManager,"TAG")

        })
    }

    override fun onCardClicked() {
        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
    }

    override fun onPromoCodeClicked() {
        promoCodeBS= PromoCodeBS()
        promoCodeBS.show(childFragmentManager,"TAG2")

    }

    override fun OnAddCard() {
        Navigation.findNavController(confirmsend).navigate(R.id.creditCardScannerFragment)

    }
}