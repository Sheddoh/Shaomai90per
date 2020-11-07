
import com.example.shaomai.Admin
import com.example.shaomai.Customer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

interface CustomerAPI {
    @GET("allcus")
    fun  retrieveCustomer(): Call<List<Customer>>


    @POST("addtocart")
    @FormUrlEncoded
    fun addtocart(
        @Field("customer_name") customer_name: String,
        @Field("customer_tel") customer_tel: Int,
        @Field("customer_mail") customer_mail: String,
        @Field("product_id") product_id: Int
        ): Call<Customer>

    @POST("register")
    @FormUrlEncoded
    fun signup(
        @Field("admin_name") admin_name: String,
        @Field("admin_tel") admin_tel: Int,
        @Field("admin_mail") admin_mail: String,
        @Field("admin_pass") admin_pass: String,
    ): Call<Admin>


    @POST("login")
    @FormUrlEncoded
    fun logincus(
        @Field("admin_mail") admin_mail: String,
        @Field("admin_pass") admin_pass: String
    ): Call<Admin>

    @FormUrlEncoded
    @PUT("customer/{customer_id}")
    fun editCustomer(
        @Path("customer_id") customer_id: String,
        @Field("customer_name") customer_name: String,
        @Field("customer_tel") customer_tel: Int,
        @Field("customer_mail") customer_mail: String,
    ): Call<Customer>




    @GET("customer/{customer_name}")
    fun retrieveCustomerID(
        @Path("customer_name") customer_id: String
    ): Call<Customer>



     @DELETE("customer/{customer_id}")
     fun deleteCustomer(
         @Path("customer_id") customer_id: String
     ): Call<Customer>







    companion object {
        fun create(): CustomerAPI {
            val cusClient: CustomerAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CustomerAPI::class.java)
            return cusClient
        }
    }





}