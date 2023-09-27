package uz.turgunboyevjurabek.zbox.madels.Order


import com.google.gson.annotations.SerializedName

data class Order_get(
    @SerializedName("client")
    val client: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mahsulot")
    val mahsulot: Int,
    @SerializedName("miqdor")
    val miqdor: Int,
    @SerializedName("nasiya")
    val nasiya: Int,
    @SerializedName("sana")
    val sana: String,
    @SerializedName("sotuvchi")
    val sotuvchi: Int,
    @SerializedName("summa")
    val summa: Int,
    @SerializedName("tolangan_summa")
    val tolanganSumma: Int
)