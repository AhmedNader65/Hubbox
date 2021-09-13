package com.hubbox.ui.home.myorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.hubbox.R
import com.hubbox.adapters.OrderStackAdapter
import com.hubbox.adapters.OrdersAdapter
import com.hubbox.databinding.BottomSheetFilterBinding
import com.hubbox.databinding.FragmentMyOrdersBinding
import com.hubbox.model.Spot


class MyOrdersFragment : Fragment(), OrderStackAdapter.OnOrderInteract {
    private lateinit var navController: NavController
    lateinit var binding: FragmentMyOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_orders, container, false)
        val mRootView = binding.getRoot()
        binding.ordersList.adapter = OrdersAdapter(createSpots(), this)
        binding.history.setOnClickListener {
            binding.history.setBackgroundColor(getColor(requireContext(), R.color.purple))
            (binding.history as MaterialButton).setIconTintResource(R.color.white)
            (binding.history as MaterialButton).setTextColor(
                getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.active.setBackgroundColor(0xF7F8FB)
            (binding.active as MaterialButton).setIconTintResource(R.color.black)
            (binding.active as MaterialButton).setTextColor(
                getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        binding.active.setOnClickListener {
            binding.active.setBackgroundColor(getColor(requireContext(), R.color.purple))
            (binding.active as MaterialButton).setIconTintResource(R.color.white)
            (binding.active as MaterialButton).setTextColor(
                getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.history.setBackgroundColor(0xF7F8FB)
            (binding.history as MaterialButton).setIconTintResource(R.color.black)
            (binding.history as MaterialButton).setTextColor(
                getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding.filter.setOnClickListener {
            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(requireContext())


            val bsBinding = BottomSheetFilterBinding.inflate(
                LayoutInflater.from(requireContext()),
                null,
                false
            )

            // on below line we are adding on click listener
            // for our dismissing the dialog button.
            bsBinding.close.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(true)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(bsBinding.root)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }
        return mRootView
    }

    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()
        spots.add(
            Spot(
                name = "Yasaka Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/Xq1ntWruZQI/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Fushimi Inari Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/NYyCqdBOKwc/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Bamboo Forest",
                city = "Kyoto",
                url = "https://source.unsplash.com/buF62ewDLcQ/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Brooklyn Bridge",
                city = "New York",
                url = "https://source.unsplash.com/THozNzxEP3g/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Empire State Building",
                city = "New York",
                url = "https://source.unsplash.com/USrZRcRS2Lw/600x800"
            )
        )
        spots.add(
            Spot(
                name = "The statue of Liberty",
                city = "New York",
                url = "https://source.unsplash.com/PeFk7fzxTdk/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Louvre Museum",
                city = "Paris",
                url = "https://source.unsplash.com/LrMWHKqilUw/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Eiffel Tower",
                city = "Paris",
                url = "https://source.unsplash.com/HN-5Z6AmxrM/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Big Ben",
                city = "London",
                url = "https://source.unsplash.com/CdVAUADdqEc/600x800"
            )
        )
        spots.add(
            Spot(
                name = "Great Wall of China",
                city = "China",
                url = "https://source.unsplash.com/AWh9C-QjhE4/600x800"
            )
        )
        return spots
    }

    override fun onOrderClicked() {
        Navigation.findNavController(binding.active).navigate(R.id.orderStatusFragment)

    }
}