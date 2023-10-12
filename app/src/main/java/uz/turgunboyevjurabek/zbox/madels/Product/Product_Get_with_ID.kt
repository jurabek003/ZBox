package uz.turgunboyevjurabek.zbox.madels.Product


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product_Get_with_ID(
    @SerializedName("hajmi")
    val hajmi: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kelgan_sana")
    val kelganSana: String,
    @SerializedName("miqdor")
    val miqdor: Int,
    @SerializedName("narx")
    val narx: Int,
    @SerializedName("nom")
    val nom: String,
    @SerializedName("olchov")
    val olchov: String,
    @SerializedName("sotuvchi")
    val sotuvchi: Int
):Serializable