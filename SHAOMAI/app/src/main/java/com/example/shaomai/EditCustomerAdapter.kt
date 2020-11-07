package com.example.shaomai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_delete_cus_layout.view.*

class EditCustomerAdapter (val items: List<Customer>, val context:Context): RecyclerView.Adapter <EditCustomerAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvName = view.tvName
        val tvMail = view.tvMail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditCustomerAdapter.ViewHolder {
       val view_item = LayoutInflater.from(parent.context).inflate(R.layout.edit_delete_cus_layout, parent, false)
        val myHolder = ViewHolder(view_item)

        view_item.setOnClickListener(){
            val pos = myHolder.adapterPosition
            val context:Context = parent.context
            val customer = items[pos]
            val intent = Intent(context, EditDeleteActivity::class.java)
            intent.putExtra("cId", customer.customer_id)
            intent.putExtra("cName", customer.customer_name)
            intent.putExtra("cTel", customer.customer_tel.toString())
            intent.putExtra("cMail", customer.customer_mail)
            context.startActivity(intent)
        }
        return myHolder
    }

    override fun onBindViewHolder(holder: EditCustomerAdapter.ViewHolder, position: Int) {
        holder.tvName.text = items[position].customer_name
        holder.tvMail.text = items[position].customer_mail
    }

    override fun getItemCount(): Int {
        return items.size
    }
}