package uz.turgunboyevjurabek.zbox.models

data class Product(
    val id: Int,
    val kelgan_sana: String,
    val miqdor: Int,
    val narx: Int,
    val nom: String,
    val olchov: String,
    val sotuvchi: Int
)