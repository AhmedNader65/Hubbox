package com.hubbox.ui.home.profile.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hubbox.R
import kotlinx.android.synthetic.main.fragment_new_location.*

class FragmentNewLocation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmloca.setOnClickListener({
            Navigation.findNavController(it).navigate(R.id.nav_add_loca_info)
        })
    }
}