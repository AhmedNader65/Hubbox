package com.hubbox.ui.home.profile.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.android.material.card.MaterialCardView
import com.hubbox.R
import kotlinx.android.synthetic.main.fragment_location_info.*


class FragmentLocationInfo : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home.setOnClickListener({
            changeBackGroundOfCard(home, hometxt, homeimg)
        })
        office.setOnClickListener({
            changeBackGroundOfCard(office, worktxt, workimg)
        })
        more.setOnClickListener({
            changeBackGroundOfCard(more, otherstxt, othersimg)
        })

        saveloca.setOnClickListener({
            Toast.makeText(requireContext(), "Location Saved", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.nav_saved_location)
        })

    }

    fun changeBackGroundOfCard(
        cardView: MaterialCardView,
        textView: TextView,
        imageView: ImageView
    ) {
        home.setCardBackgroundColor(resources.getColor(R.color.white))
        more.setCardBackgroundColor(resources.getColor(R.color.white))
        office.setCardBackgroundColor(resources.getColor(R.color.white))

        homeimg.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.grey_70),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        othersimg.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.grey_70),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        workimg.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.grey_70),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        hometxt.setTextColor(resources.getColor(R.color.delivery_black))
        otherstxt.setTextColor(resources.getColor(R.color.delivery_black))
        worktxt.setTextColor(resources.getColor(R.color.delivery_black))

        cardView.setCardBackgroundColor(resources.getColor(R.color.purple))
        textView.setTextColor(resources.getColor(R.color.white))
        imageView.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.white),
            android.graphics.PorterDuff.Mode.SRC_IN
        )


    }
}