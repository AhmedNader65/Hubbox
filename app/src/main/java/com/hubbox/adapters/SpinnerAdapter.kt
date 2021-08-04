package com.hubbox.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.hubbox.R


class SpinnerAdapter(
    private val cont: Context, resourceId: Int,
    private val objects: List<String>
) : ArrayAdapter<String?>(cont, resourceId, objects) {
    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.e("customerview", "here$position")
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row: View = inflater.inflate(R.layout.item_country_dropdown, parent, false)
        val img = row.findViewById<ImageView>(R.id.ivCountry)
        val arrowImg = row.findViewById<ImageView>(R.id.arrow)
        if (position == 0) {
            img.setImageResource(R.drawable.ic_flag_uae)
        } else {
//            img.setImageResource(R.drawable.english_flag)
        }

//        if (position == 0) {//Special style for dropdown header
//        label.setTextColor(context.getResources().getColor(R.color.text_hint_color));
//        }
        return row
    }
}
