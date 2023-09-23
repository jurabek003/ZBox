package uz.turgunboyevjurabek.zbox.network

import retrofit2.Call
import retrofit2.http.GET
import uz.turgunboyevjurabek.zbox.madels.Clients

interface ApiServis {
    @GET("clientlar/")
    fun getClients():Call<ArrayList<Clients>>
}