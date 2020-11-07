package com.example.shaomai

import CustomerAPI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_check__customer__list.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.edit_delete_cus_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductDetail : AppCompatActivity() {
    val createClient : CustomerAPI = CustomerAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val pID = intent.getStringExtra("pID")
        val pName = intent.getStringExtra("pName")
        val pDes = intent.getStringExtra("pDes")
        val pPrice = intent.getStringExtra("pPrice")
        val pImg = intent.getStringExtra("pImg")

        productID.setText(pID)
        productName.setText(pName)
        descrip.setText(pDes)
        test.setText(pPrice)
        Picasso.get().load(pImg).into(img_product)
    }

    override fun onResume() {
        super.onResume()
        callProduct()
    }

    fun callProduct(){

    }



    fun AddtoCart(v: View){
//        val serv: CustomerAPI = Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:3000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CustomerAPI::class.java)
//        serv.addtocart(
//            edt_name.text.toString(),
//            edt_tel.text.toString().toInt(),
//            edt_mail.text.toString(),
//            edt_code.toString().toInt()
//        ).enqueue(object :Callback<Customer>{
//            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
//                if(response.isSuccessful()){
//                    Toast.makeText(applicationContext, "Send Data Customer", Toast.LENGTH_LONG).show()
//                    finish()
//                }else{
//                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onFailure(call: Call<Customer>, t: Throwable) {
//                Toast.makeText(applicationContext, "Error onFailure " + t.message,Toast.LENGTH_LONG).show()
//            }
//        })

        createClient.addtocart(
            edt_name.text.toString(),
            edt_tel.text.toString().toInt(),
            edt_mail.text.toString(),
            edt_code.text.toString().toInt()
            ) .enqueue(object : Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>){
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Save", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Customer>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
            }
        })

    }


}