package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemOtherOrdersBinding
import com.hubbox.model.Spot

class OtherOrdersAdapter(
    private val values: List<Spot>
) : RecyclerView.Adapter<OtherOrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemOtherOrdersBinding.inflate(
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

    inner class ViewHolder(binding: ItemOtherOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }
}