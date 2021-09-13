package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemOrderCardBinding
import com.hubbox.model.Spot

class OrdersAdapter(private val values: List<Spot>, val mListener: OrderStackAdapter.OnOrderInteract
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemOrderCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.itemView.setOnClickListener { v ->
            mListener.onOrderClicked()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemOrderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        val companyName: TextView = binding.companyName

    }


    interface OnOrderInteract {
        fun onOrderClicked()
    }
}