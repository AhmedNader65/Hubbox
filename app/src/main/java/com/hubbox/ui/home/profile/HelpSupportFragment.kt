package com.hubbox.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.model.LatLng
import com.hubbox.R
import com.hubbox.adapters.FAQAdapter
import com.hubbox.adapters.HomeAddressAdapter
import com.hubbox.model.FAQ
import com.hubbox.model.HomeAddress
import kotlinx.android.synthetic.main.fragment_help_support.*
import kotlinx.android.synthetic.main.fragment_saved_location.*
import kotlinx.android.synthetic.main.fragment_saved_location.homerv

class HelpSupportFragment : Fragment() {
    lateinit var faq: java.util.ArrayList<FAQ>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help_support, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        faq= ArrayList()
        faq.add(FAQ("a",""))
        faq.add(FAQ("a",""))
        faqrv.adapter= FAQAdapter(faq)
    }
}