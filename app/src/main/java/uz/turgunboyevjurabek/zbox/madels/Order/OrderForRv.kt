package uz.turgunboyevjurabek.zbox.madels.Order

import com.google.gson.annotations.SerializedName

class OrderForRv {
    var client: Int?=null
    var id: Int?=null
    var mahsulot:  Int?=null
    var miqdor:  Int?=null
    var nasiya:  Int?=null
    var sana: String?=null
    var sotuvchi:  Int?=null
    var summa:  Int?=null
    var tolanganSumma:  Int?=null
    var nomi:String?=null

    constructor(
        client: Int?,
        id: Int?,
        mahsulot: Int?,
        miqdor: Int?,
        nasiya: Int?,
        sana: String?,
        sotuvchi: Int?,
        summa: Int?,
        tolanganSumma: Int?,
        nomi: String?,
    ) {
        this.client = client
        this.id = id
        this.mahsulot = mahsulot
        this.miqdor = miqdor
        this.nasiya = nasiya
        this.sana = sana
        this.sotuvchi = sotuvchi
        this.summa = summa
        this.tolanganSumma = tolanganSumma
        this.nomi = nomi
    }

    constructor(
        client: Int?,
        id: Int?,
        mahsulot: Int?,
        miqdor: Int?,
        nasiya: Int?,
        sana: String?,
        sotuvchi: Int?,
        summa: Int?,
        tolanganSumma: Int?,
    ) {
        this.client = client
        this.id = id
        this.mahsulot = mahsulot
        this.miqdor = miqdor
        this.nasiya = nasiya
        this.sana = sana
        this.sotuvchi = sotuvchi
        this.summa = summa
        this.tolanganSumma = tolanganSumma
    }


}
