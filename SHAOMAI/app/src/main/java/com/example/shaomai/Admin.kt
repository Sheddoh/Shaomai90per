package com.example.shaomai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Admin (

    @Expose
    @SerializedName("admin_id") val admin_id :Int,
    @Expose
    @SerializedName("admin_name") val admin_name: String,
    @Expose
    @SerializedName("admin_tel") val admin_tel: Int,
    @Expose
    @SerializedName("admin_mail") val admin_mail : String,
    @Expose
    @SerializedName("admin_pass") val admin_pass: String){}