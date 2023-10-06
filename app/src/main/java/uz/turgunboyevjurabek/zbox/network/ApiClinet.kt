package uz.turgunboyevjurabek.zbox.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClinet {
    // ipi yangilangandan keyingi holat
    const val BASE_URl="https://omborapi11.pythonanywhere.com/"

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URl)
            .build()
    }
    fun getApiServis(context: Context):ApiServis{
        return getRetrofit().create(ApiServis::class.java)
    }

}