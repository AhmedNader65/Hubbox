package com.hubbox.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.R
import com.hubbox.model.Spot

class OrderStackAdapter(
    private var spots: List<Spot> = emptyList(), val mListener: OnOrderInteract
) : RecyclerView.Adapter<OrderStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_home_orders, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]
//        holder.name.text = "${spot.id}. ${spot.name}"
//        holder.city.text = spot.city

        holder.itemView.setOnClickListener { v ->
            mListener.onOrderClicked()
        }
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<Spot>) {
        this.spots = spots
    }

    fun getSpots(): List<Spot> {
        return spots
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val name: TextView = view.findViewById(R.id.item_name)
//        var city: TextView = view.findViewById(R.id.item_city)
//        var image: ImageView = view.findViewById(R.id.item_image)
    }


    interface OnOrderInteract {
        fun onOrderClicked()
    }
}