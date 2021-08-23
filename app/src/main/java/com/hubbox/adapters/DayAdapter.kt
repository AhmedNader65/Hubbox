package com.hubbox.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.hubbox.R
import com.hubbox.databinding.ItemDayBinding
import com.hubbox.databinding.ItemOrderCardBinding
import com.hubbox.databinding.ItemServiceTypeBinding
import com.hubbox.model.Day
import com.hubbox.model.Spot
import com.hubbox.model.Vehicle

class DayAdapter(
    private val values: List<Day>
) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {
    private var lastSelected: MaterialCardView? = null
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemDayBinding.inflate(
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

    inner class ViewHolder(binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Day) {
            name.text = item.name
            day.text = item.day.toString()
        }

        val name: TextView = binding.dayText
        val day: TextView = binding.dayNum

        init {
            binding.day.setOnClickListener {

                (it as MaterialCardView).setCardBackgroundColor(context.resources.getColor(R.color.purple))
                it.setStrokeColor(context.resources.getColor(R.color.purple))
                day.setTextColor(context.resources.getColor(R.color.white))
                name.setTextColor(context.resources.getColor(R.color.white))
                lastSelected?.setCardBackgroundColor(context.resources.getColor(R.color.white))
                lastSelected?.findViewById<TextView>(R.id.dayText)
                    ?.setTextColor(context.resources.getColor(R.color.black))
                lastSelected?.findViewById<TextView>(R.id.dayNum)
                    ?.setTextColor(context.resources.getColor(R.color.black))
                lastSelected?.setStrokeColor(context.resources.getColor(R.color.stroke_grey))
                lastSelected = it
            }
        }

    }

}