package com.hubbox.ui.home.paymentCards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_card.*

class AddCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = layoutInflater.inflate(com.hubbox.R.layout.fragment_add_card, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardNum.editText!!.setText(requireArguments().getString("cardNum"))
        cardHolder.editText!!.setText(requireArguments().getString("cardHolder"))
        expiryDate.editText!!.setText(requireArguments().getString("exp"))
        creditNum.setText(requireArguments().getString("cardNum"))
        holder.setText(requireArguments().getString("cardHolder"))
        exp.setText(requireArguments().getString("exp"))
    }

}