package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemPaymentCardsBinding
import com.hubbox.model.Spot

class PickCardAdapter(private val values: List<Spot>
) : RecyclerView.Adapter<PickCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemPaymentCardsBinding.inflate(
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

    inner class ViewHolder(binding: ItemPaymentCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        val companyName: TextView = binding.companyName

    }

}