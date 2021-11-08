package com.hubbox.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hubbox.R
import com.hubbox.databinding.ItemOrderCardBinding
import com.hubbox.model.Address
import com.hubbox.model.Spot
import com.hubbox.utils.OrderStatus

class OrdersAdapter(
    private val values: List<Spot>,
    val mListener: OrderStackAdapter.OnOrderInteract,
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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
        holder.bind(item)



        holder.itemView.setOnClickListener { v ->
            mListener.onOrderClicked()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemOrderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Spot) {
            if (item.orderStatus == OrderStatus.WAITING) {
                point1.setImageResource(R.drawable.ic_waiting_for_delivery)
                point2.setImageResource(R.drawable.ic_grey_dot2)
                point3.setImageResource(R.drawable.ic_grey_dot2)
                point4.setImageResource(R.drawable.ic_grey_dot2)
                point5.setImageResource(R.drawable.ic_grey_dot2)
                line1.setImageResource(R.drawable.ic_grey_line)
                line2.setImageResource(R.drawable.ic_grey_line)
                line3.setImageResource(R.drawable.ic_grey_line)
                line4.setImageResource(R.drawable.ic_grey_line)
                orderstatustext.setText("Waiting for Pickup")
                orderstatustext.setTextColor(context.resources.getColor(R.color.black))

            }
            else if (item.orderStatus==OrderStatus.PICKEDUP){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_picked_up)
                point3.setImageResource(R.drawable.ic_grey_dot2)
                point4.setImageResource(R.drawable.ic_grey_dot2)
                point5.setImageResource(R.drawable.ic_grey_dot2)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_grey_line)
                line3.setImageResource(R.drawable.ic_grey_line)
                line4.setImageResource(R.drawable.ic_grey_line)
                orderstatustext.setText("picked up")
                orderstatustext.setTextColor(context.resources.getColor(R.color.black))
            }
            else if (item.orderStatus==OrderStatus.TRANSIT){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_purple_dot)
                point3.setImageResource(R.drawable.ic_in_transient)
                point4.setImageResource(R.drawable.ic_grey_dot2)
                point5.setImageResource(R.drawable.ic_grey_dot2)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_purple_line)
                line3.setImageResource(R.drawable.ic_grey_line)
                line4.setImageResource(R.drawable.ic_grey_line)
                orderstatustext.setText("in transit")
                orderstatustext.setTextColor(context.resources.getColor(R.color.black))
            }
            else if (item.orderStatus==OrderStatus.OUTFORDELIVERY){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_purple_dot)
                point3.setImageResource(R.drawable.ic_purple_dot)
                point4.setImageResource(R.drawable.ic_out_for_delivery)
                point5.setImageResource(R.drawable.ic_grey_dot2)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_purple_line)
                line3.setImageResource(R.drawable.ic_purple_line)
                line4.setImageResource(R.drawable.ic_grey_line)
                orderstatustext.setText("out of delivery")
                orderstatustext.setTextColor(context.resources.getColor(R.color.black))
            }
            else if (item.orderStatus==OrderStatus.DELIVERED){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_purple_dot)
                point3.setImageResource(R.drawable.ic_purple_dot)
                point4.setImageResource(R.drawable.ic_purple_dot)
                point5.setImageResource(R.drawable.ic_approved)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_purple_line)
                line3.setImageResource(R.drawable.ic_purple_line)
                line4.setImageResource(R.drawable.ic_purple_line)
                orderstatustext.setText("delivered")
                orderstatustext.setTextColor(context.resources.getColor(R.color.green))
            }
            else if (item.orderStatus==OrderStatus.CANCELLED){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_purple_dot)
                point3.setImageResource(R.drawable.ic_purple_dot)
                point4.setImageResource(R.drawable.ic_purple_dot)
                point5.setImageResource(R.drawable.ic_cancelled)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_purple_line)
                line3.setImageResource(R.drawable.ic_purple_line)
                line4.setImageResource(R.drawable.ic_purple_line)
                orderstatustext.setText("cancelled")
                orderstatustext.setTextColor(context.resources.getColor(R.color.red))
            }
            else if (item.orderStatus==OrderStatus.RETURNED){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_purple_dot)
                point3.setImageResource(R.drawable.ic_purple_dot)
                point4.setImageResource(R.drawable.ic_purple_dot)
                point5.setImageResource(R.drawable.ic_returned)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_purple_line)
                line3.setImageResource(R.drawable.ic_purple_line)
                line4.setImageResource(R.drawable.ic_purple_line)
                orderstatustext.setText("returned")
                orderstatustext.setTextColor(context.resources.getColor(R.color.selected_blue))
            }
            else if (item.orderStatus==OrderStatus.RETURNED){
                point1.setImageResource(R.drawable.ic_purple_dot)
                point2.setImageResource(R.drawable.ic_purple_dot)
                point3.setImageResource(R.drawable.ic_purple_dot)
                point4.setImageResource(R.drawable.ic_purple_dot)
                point5.setImageResource(R.drawable.ic_due_date)
                line1.setImageResource(R.drawable.ic_purple_line)
                line2.setImageResource(R.drawable.ic_purple_line)
                line3.setImageResource(R.drawable.ic_purple_line)
                line4.setImageResource(R.drawable.ic_purple_line)
                orderstatustext.setText("returned")
                orderstatustext.setTextColor(context.resources.getColor(R.color.quantum_yellow))
            }
        }


        val point1: ImageView = binding.point1
        val point2: ImageView = binding.point2
        val point3: ImageView = binding.point3
        val point4: ImageView = binding.point4
        val point5: ImageView = binding.point5
        val line1: ImageView = binding.line1
        val line2: ImageView = binding.line2
        val line3: ImageView = binding.line3
        val line4: ImageView = binding.line4
        val orderstatustext: TextView = binding.deliverystatustext

    }


    interface OnOrderInteract {
        fun onOrderClicked()
    }
}