package uz.turgunboyevjurabek.zbox.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.turgunboyevjurabek.zbox.Seller.getSeller
import uz.turgunboyevjurabek.zbox.Seller.getSellers
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

    //Client id orqli olish
    @GET("clientlar/{id}/")
    fun getClientId(@Path("id") id :Int):Call<Clients_Get>

    //Mahsulotlarni hammasini olish uchun
    @GET("mahsulotlar/")
    fun getProduct():Call<ArrayList<Product>>

    //Sotuvchilarni hammasini olish uchun
    @GET("sotuvchilar/")
    fun getSellers():Call<ArrayList<getSellers>>

    //sotuchini id orqali olish
    @GET("/sotuvchilar/{id}/")
    fun getSeller(@Path("id") id:Int):Call<getSeller>
}