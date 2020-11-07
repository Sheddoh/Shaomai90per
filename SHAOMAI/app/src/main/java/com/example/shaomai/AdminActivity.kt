package com.example.shaomai

import CustomerAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_check__customer__list.*
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product.recycler_view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

    }
    fun CustomerList(v:View){
        val intent = Intent(this, Check_Customer_List::class.java)
        startActivity(intent)

    }

    fun ProductList(v:View){
        val intent = Intent(this, Check_Product_List::class.java)
        startActivity(intent)
    }



}