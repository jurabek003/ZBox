package uz.turgunboyevjurabek.zbox.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.turgunboyevjurabek.zbox.madels.Client_Post_Request
import uz.turgunboyevjurabek.zbox.madels.Clients_Get
import uz.turgunboyevjurabek.zbox.madels.Clients_Post
import uz.turgunboyevjurabek.zbox.madels.Product

interface ApiServis {


    //Clientlarni hammasini olish uchun
    @GET("clientlar/")
    fun getClients():Call<ArrayList<Clients_Get>>


    //Client qo'shish uchun
    @POST("clientlar/")
    fun postClient(@Body clientPostRequest: Client_Post_Request):Call<ArrayList<Clients_Post>>


    //Mahsulotlarni hammasini olish uchun
    @GET("mahsulotlar/")
    fun getProduct():Call<ArrayList<Product>>
}