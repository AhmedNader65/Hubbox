package com.hubbox.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.R
import com.hubbox.databinding.ItemOrderCardBinding
import com.hubbox.databinding.ItemServiceTypeBinding
import com.hubbox.model.Spot
import com.hubbox.model.Vehicle

class VehicleAdapter(
    private val values: List<Vehicle>
) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {
    private var lastSelected: CardView? = null
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemServiceTypeBinding.inflate(
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

    inner class ViewHolder(binding: ItemServiceTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vehicle) {
            name.text = item.name
            size.text = item.size
            upto.text = "Up to ${item.upto}KG"
            image.setImageResource(item.image)
        }

        val name: TextView = binding.name
        val upto: TextView = binding.upTo
        val size: TextView = binding.size
        val image: ImageView = binding.image

        init {
            binding.vehicle.setOnClickListener {

                (it as CardView).setCardBackgroundColor(context.resources.getColor(R.color.selected_green))
                lastSelected?.setCardBackgroundColor(context.resources.getColor(R.color.unselected_card))
                lastSelected = it
            }
        }

    }

}