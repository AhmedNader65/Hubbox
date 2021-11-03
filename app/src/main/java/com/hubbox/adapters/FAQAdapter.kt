package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemAddressBinding
import com.hubbox.databinding.ItemFaqBinding
import com.hubbox.databinding.ItemLocationBinding
import com.hubbox.databinding.ItemNotificationBinding
import com.hubbox.model.Address
import com.hubbox.model.FAQ
import com.hubbox.model.HomeAddress
import com.hubbox.model.Spot
import kotlinx.android.synthetic.main.item_address.view.*

class FAQAdapter(
    private val values: List<FAQ>
) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemFaqBinding.inflate(
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

    inner class ViewHolder(binding: ItemFaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FAQ) {

        }


        init {

        }
    }




}