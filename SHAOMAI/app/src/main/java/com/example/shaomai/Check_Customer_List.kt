package com.example.shaomai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_check__customer__list.*
import kotlinx.android.synthetic.main.activity_check__product__list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Check_Customer_List : AppCompatActivity() {
    var cusList = arrayListOf<Customer>()
    val createClient = CustomerAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check__customer__list)

        recycler_viewCus.layoutManager = LinearLayoutManager(applicationContext)
        recycler_viewCus.addItemDecoration(DividerItemDecoration(recycler_viewCus.context,
        DividerItemDecoration.VERTICAL))
//        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
//        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
//        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))
//
//        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
//            override fun onItemClicked(position: Int, view: View) {
//                Toast.makeText(
//                    applicationContext, "You click on : " +cusList[position].product_id,
//                    Toast.LENGTH_SHORT).show()
//                val customer = cusList[position]
//                val intent = Intent(applicationContext, EditDeleteActivity::class.java)
//                intent.putExtra("pId", customer.product_id.toString())
//                intent.putExtra("cName", customer.customer_name)
//                intent.putExtra("cTel", customer.customer_tel)
//                intent.putExtra("cMail", customer.customer_mail)
//                startActivity(intent)
//            }
//        })
    }

    override fun onResume() {
        super.onResume()
        callCustomer()
    }

    fun callCustomer(){
        cusList.clear()
        createClient.retrieveCustomer()
            .enqueue(object : Callback<List<Customer>>{
                override fun onResponse(
                    call: Call<List<Customer>>,
                    response: Response<List<Customer>>) {
                    response.body()?.forEach{
                        cusList.add(Customer(it.customer_id, it.customer_name, it.customer_tel, it.customer_mail,it.product_id))
                    }
                    recycler_viewCus.adapter = EditCustomerAdapter(cusList, applicationContext)
                }

                override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            })
    }


    fun SearchCus(v: View){

    }
}