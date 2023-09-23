package uz.turgunboyevjurabek.zbox.network

import retrofit2.Call
import retrofit2.http.GET
import uz.turgunboyevjurabek.zbox.madels.Clients
import uz.turgunboyevjurabek.zbox.madels.Product

interface ApiServis {
    @GET("clientlar/")
    fun getClients():Call<ArrayList<Clients>>

    @GET("mahsulotlar/")
    fun getProduct():Call<ArrayList<Product>>
}