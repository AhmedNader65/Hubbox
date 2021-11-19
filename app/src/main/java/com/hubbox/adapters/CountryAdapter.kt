package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemAddressBinding
import com.hubbox.databinding.ItemCountrySearchBinding
import com.hubbox.databinding.ItemNotificationBinding
import com.hubbox.model.Address
import com.hubbox.model.Country
import com.hubbox.model.Spot
import kotlinx.android.synthetic.main.item_address.view.*

class CountryAdapter(
    private val values: List<Country>, val mListener: OnCountryInteracted
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemCountrySearchBinding.inflate(
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

    inner class ViewHolder(binding: ItemCountrySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            country.text = item.name
        }

        val flag: ImageView = binding.ivCountry
        val country: TextView = binding.name
        init {
            binding.root.setOnClickListener {
                mListener.onCountryChoosen(values[adapterPosition])
            }
        }
    }

    interface OnCountryInteracted {
        fun onCountryChoosen(country: Country)
    }


}