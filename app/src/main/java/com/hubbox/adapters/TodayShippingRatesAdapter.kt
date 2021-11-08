package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.R
import com.hubbox.databinding.*
import com.hubbox.model.*
import kotlinx.android.synthetic.main.item_address.view.*

class TodayShippingRatesAdapter(
    private val values: List<ShippingRate>
) : RecyclerView.Adapter<TodayShippingRatesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemShipmentDetailsBinding.inflate(
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

    inner class ViewHolder(binding: ItemShipmentDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShippingRate) {



        }
        init {


        }
    }




}