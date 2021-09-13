package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemOtherOrdersBinding
import com.hubbox.databinding.ItemStatusBinding
import com.hubbox.model.Spot

class OrderStatusAdapter(
    private val values: List<Spot>
) : RecyclerView.Adapter<OrderStatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }
}