package com.example.shaomai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Check_Product_List : AppCompatActivity() {
    var productList = arrayListOf<Product>()
    val createClient = ProductAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check__product__list)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))

        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(
                    applicationContext, "Click : "+ productList[position].product_name,
                    Toast.LENGTH_LONG).show()
                val pro = productList[position]
                val intent = Intent(applicationContext, ProductDetail::class.java)
                intent.putExtra("pID", pro.product_id.toString())
                intent.putExtra("pName", pro.product_name)
                intent.putExtra("pDes", pro.product_description)
                intent.putExtra("pPrice", pro.product_price_rent)
                intent.putExtra("pImg", pro.product_img)
                startActivity(intent)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        callProductData()
    }

    fun callProductData(){
        productList.clear();
        val serv : ProductAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI ::class.java)
        serv.retrieveProduct()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    response.body()?.forEach {
                        productList.add(Product(it.product_id, it.product_name,it.product_price_rent,it.product_price_fine,it.product_description,it.product_img, it.product_status)) }
                    recycler_view.adapter = ProductAdapter(productList, applicationContext)

                }
                override fun onFailure(call: Call<List<Product>>, t: Throwable) = t.printStackTrace()
            }) }



    fun clickInsert(v:View){
        val intent = Intent(this, InsertActivityProduct::class.java)
        startActivity(intent)
    }



}