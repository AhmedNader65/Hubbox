package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemCreditcardPaymentsBinding
import com.hubbox.databinding.ItemPaymentCardsBinding
import com.hubbox.model.Spot

class OtherCardsAdpter(private val values: List<Spot>
) : RecyclerView.Adapter<OtherCardsAdpter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemCreditcardPaymentsBinding.inflate(
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

    inner class ViewHolder(binding: ItemCreditcardPaymentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        val companyName: TextView = binding.companyName

    }

}