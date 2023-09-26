package uz.turgunboyevjurabek.zbox.models


import com.google.gson.annotations.SerializedName

data class Clients_Post(
    @SerializedName("fam")
    val fam: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ism")
    val ism: String,
    @SerializedName("manzil")
    val manzil: String,
    @SerializedName("tel")
    val tel: String,
    @SerializedName("umumiy_summa")
    val umumiySumma: Int
)