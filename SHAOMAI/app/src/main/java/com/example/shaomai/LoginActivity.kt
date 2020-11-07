package com.example.shaomai

import CustomerAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    var userList  = arrayListOf<Admin>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }


    fun Login(v: View){
        val intent = Intent(this, AdminActivity::class.java)
        userList.clear()
        val api : CustomerAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CustomerAPI :: class.java)

        api.logincus(
            input_email.text.toString(),
            input_password.text.toString()).enqueue(object : Callback<Admin>{
            override fun onResponse(call: Call<Admin>,response: Response<Admin>){
                userList.add(
                    Admin(response.body()?.admin_id.toString().toInt(),
                    response.body()?.admin_name.toString(),
                    response.body()?.admin_tel.toString().toInt(),
                    response.body()?.admin_mail.toString(),
                    response.body()?.admin_pass.toString()
                )
                )
                if (response.isSuccessful()){
                    Toast.makeText(applicationContext,"Login Successfull !!",Toast.LENGTH_SHORT).show()

                    intent.putExtra("mUid",response.body()?.admin_id.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"error !",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Admin>,t: Throwable){
                Toast.makeText(applicationContext,"Incorrect !"+t.message,Toast.LENGTH_LONG).show()
            }
        })

    }


    fun goSign(v:View){
        val sign = Intent(this,RegisterActivity::class.java)
        startActivity(sign)
    }


}

