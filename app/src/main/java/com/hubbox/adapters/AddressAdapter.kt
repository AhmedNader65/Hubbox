package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemAddressBinding
import com.hubbox.databinding.ItemNotificationBinding
import com.hubbox.model.Address
import com.hubbox.model.Spot
import kotlinx.android.synthetic.main.item_address.view.*

class AddressAdapter(
    private val values: List<Address>, val mListener: OnAddressInteract
) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemAddressBinding.inflate(
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

    inner class ViewHolder(binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Address) {
            address.text = item.address
            country.text = item.country
        }

        val address: TextView = binding.address
        val country: TextView = binding.country
        init {
            binding.root.setOnClickListener {
                mListener.onAddressChoosen(values[adapterPosition])
            }
        }
    }

    interface OnAddressInteract {
        fun onAddressChoosen(address:Address)
    }


}