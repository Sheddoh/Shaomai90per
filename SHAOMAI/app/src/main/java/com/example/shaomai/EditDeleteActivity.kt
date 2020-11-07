package com.example.shaomai

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_delete.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDeleteActivity : AppCompatActivity() {
    val createClient = CustomerAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete)

        val pId = intent.getStringExtra("pId")
        val cName = intent.getStringExtra("cName")
        val cTel = intent.getStringExtra("cTel")
        val cMail = intent.getStringExtra("cMail")

        edt_id.setText(pId)
        edt_id.isEnabled = false
        edt_name.setText(cName)
        edt_tel.setText(cTel)
        edt_mail.setText(cMail)


    }

    fun CusSave(v: View){
        createClient.editCustomer(
            edt_id.text.toString(),
            edt_name.text.toString(),
            edt_tel.text.toString().toInt(),
            edt_mail.text.toString()
        )
            .enqueue(object: Callback<Customer>{
                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext, "Edit Sucess",Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "Edit Fail",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Customer>, t: Throwable)  {
                    Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
                }
            })
    }

    fun CusDel(v:View){

        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            createClient.deleteCustomer(edt_id.text.toString()).enqueue(object : Callback<Customer> {
                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
            finish()
        }
        val negativeButtonClick = { dialog: DialogInterface, which :Int-> dialog.cancel() }
        builder.setTitle("Warning")
        builder.setMessage("Do you want to delete the Dorm?")
        builder.setPositiveButton("No", negativeButtonClick)
        builder.setNegativeButton("Yes", positiveButtonClick)
        builder.show()
    }



}