package com.example.shaomai


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ProductAPI {
    @GET("allproduct") /// Call NodeJS
// @GET("allmovie.php") // Call PHP file
    fun retrieveProduct(): Call<List<Product>>





    @GET("product/{product_id}")
    fun retrivePID(
        @Path("product_id") product_id:Int): Call<Product>



    @GET("product/{product_name}")
    fun retrieveProductID(
        @Path("product_name") product_name: String
    ): Call<Product>

    @POST("product")
    @FormUrlEncoded
    fun insertpro(
        @Field("product_name") product_name: String,
        @Field("product_price_rent") product_price_rent: Int,
        @Field("product_price_fine") product_price_fine: Int,
        @Field("product_description") product_description: String,
        @Field("product_img") product_img: String,
        @Field("product_status")product_status: Int): Call<Product>

    companion object{
        fun create(): ProductAPI{
            val dormClient : ProductAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductAPI ::class.java)
            return dormClient
        }
    }

}