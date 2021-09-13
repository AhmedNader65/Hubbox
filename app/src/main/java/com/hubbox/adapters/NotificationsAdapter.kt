package com.hubbox.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.databinding.ItemNotificationBinding
import com.hubbox.model.Spot

class NotificationsAdapter(
    private val values: List<Spot>, val mListener: OnNotificationInteract
) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemNotificationBinding.inflate(
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

    inner class ViewHolder(binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        val companyName: TextView = binding.companyName
        init {
            binding.notificationActionButton.setOnClickListener {
                mListener.onTrackOrder()
            }
        }
    }

    interface OnNotificationInteract {
        fun onTrackOrder()
    }


}