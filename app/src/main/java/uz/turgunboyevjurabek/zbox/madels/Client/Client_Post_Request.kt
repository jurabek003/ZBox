package uz.turgunboyevjurabek.zbox.madels.Client

import com.google.gson.annotations.SerializedName

data class Client_Post_Request (
    @SerializedName("ism")
    val ism: String,
    @SerializedName("fam")
    val fam: String,
    @SerializedName("manzil")
    val manzil: String,
    @SerializedName("tel")
    val tel: String,
    @SerializedName("umumiy_summa")
    val umumiySumma: Int
)