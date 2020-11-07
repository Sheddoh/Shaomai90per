package com.example.shaomai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_item_layout.view.*

class ProductAdapter(val items: List<Product>, val context: Context): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        return ViewHolder(view_item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.product_name?.text = items[position].product_name
        holder.product_description?.text = items[position].product_description
        holder.product_price_rent?.text = items[position].product_price_rent.toString()
        Glide.with(context)
            .load(items[position].product_img)
            .into(holder.product_img)
    }
    override fun getItemCount(): Int {
        return items.size
    }
}
class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val product_name = view.name
        val product_img = view.img
        val product_description = view.descrip
        val product_price_rent = view.price_rent

}


