package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemAddressBinding
import com.hubbox.databinding.ItemCitySearchBinding
import com.hubbox.databinding.ItemCountrySearchBinding
import com.hubbox.databinding.ItemNotificationBinding
import com.hubbox.model.Address
import com.hubbox.model.City
import com.hubbox.model.Country
import com.hubbox.model.Spot
import kotlinx.android.synthetic.main.item_address.view.*

class CityAdapter(
    private val values: List<City>, val mListener: OnCityInteracted
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemCitySearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemCitySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: City) {
            country.text = item.name
        }

        val country: TextView = binding.name
        init {
            binding.root.setOnClickListener {
                mListener.onCityChoosen(values[adapterPosition])
            }
        }
    }

    interface OnCityInteracted {
        fun onCityChoosen(country: City)
    }


}