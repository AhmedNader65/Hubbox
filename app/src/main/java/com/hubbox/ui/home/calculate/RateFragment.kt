package com.hubbox.ui.home.calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.hubbox.R
import kotlinx.android.synthetic.main.fragment_rate.*


class RateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        process.setOnClickListener({
            Toast.makeText(it.context, "Done", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.navigation_home)
        })
    }
}