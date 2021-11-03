package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemAddressBinding
import com.hubbox.databinding.ItemLocationBinding
import com.hubbox.databinding.ItemNotificationBinding
import com.hubbox.model.Address
import com.hubbox.model.HomeAddress
import com.hubbox.model.Spot
import kotlinx.android.synthetic.main.item_address.view.*

class HomeAddressAdapter(
    private val values: List<HomeAddress>, val mListener: OnAddressInteract
) : RecyclerView.Adapter<HomeAddressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemLocationBinding.inflate(
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

    inner class ViewHolder(binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeAddress) {

        }


        init {
            binding.root.setOnClickListener {
                mListener.onAddressChoosen(values[adapterPosition])
            }
        }
    }

    interface OnAddressInteract {
        fun onAddressChoosen(address:HomeAddress)
    }


}