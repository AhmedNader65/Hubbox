package com.hubbox.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hubbox.R
import com.hubbox.databinding.ItemCardHistoryBinding
import com.hubbox.databinding.ItemOrderCardBinding
import com.hubbox.model.Spot

class CardHistoryAdapter(private val values: List<Spot>
) : RecyclerView.Adapter<CardHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemCardHistoryBinding.inflate(
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

    inner class ViewHolder(binding: ItemCardHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        val companyName: TextView = binding.companyName

    }

}