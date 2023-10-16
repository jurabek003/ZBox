package uz.turgunboyevjurabek.zbox.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.Seller.getSeller
import uz.turgunboyevjurabek.zbox.databinding.FragmentOrderAboutBinding
import uz.turgunboyevjurabek.zbox.madels.Client.Clients_Get
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID
import uz.turgunboyevjurabek.zbox.network.ApiClinet

class OrderAboutFragment : Fragment() {
    private val binding by lazy { FragmentOrderAboutBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<Order_get>
    private lateinit var list2:ArrayList<Product_Get_with_ID>
    private  var sellerId:Int?=null
    private  var clientId:Int?=null
    private var abs:Boolean=false
    private lateinit var listSeller:ArrayList<getSeller>
    private lateinit var listClient:ArrayList<Clients_Get>
    private var position:Int?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        listClient= ArrayList()
        listSeller= ArrayList()
        list= ArrayList()
        list2= ArrayList()

        list=arguments?.getStringArrayList("keyOrder")  as ArrayList<Order_get>
        list2=arguments?.getStringArrayList("keyProduct")  as ArrayList<Product_Get_with_ID>
        position=arguments?.getInt("keyPosition")

        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()

        sellerId=list[position!!].sotuvchi
        clientId=list[position!!].client


        getASellerAndClient()

        if (abs){

        }
        return binding.root
    }



    private fun getASellerAndClient() {

        val apiServis= ApiClinet.getApiServis(requireContext())
        if (sellerId != null && clientId != null){
            // sotuvchi uchun
            apiServis.getSeller(sellerId!!).enqueue(object :Callback<getSeller>{
                override fun onResponse(call: Call<getSeller>, response: Response<getSeller>) {
                    if (response.isSuccessful){
                        listSeller.add(response.body()!!)
                        binding.thtSellerName.text=listSeller.get(0).ism
                        binding.thtSellerName.visibility=View.VISIBLE
                        binding.lottiSellerName.visibility=View.GONE

                        Toast.makeText(requireContext(), "seller da hammasi yahshi", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "else ga tushdi ${response.message()}", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<getSeller>, t: Throwable) {

                }
            })
            // klient uchun
            apiServis.getClientId(clientId!!).enqueue(object :Callback<Clients_Get>{
                override fun onResponse(call: Call<Clients_Get>, response: Response<Clients_Get>) {
                    if (response.isSuccessful){
                        listClient.add(response.body()!!)
                        binding.thtClientName.text=listClient.get(0).ism
                        binding.thtClientName.visibility=View.VISIBLE
                        binding.lottiClientName.visibility=View.GONE
                        Toast.makeText(requireContext(), "clinet da hammasi yahshi", Toast.LENGTH_SHORT).show()

                        responsGet()


                    }else{
                        Toast.makeText(requireContext(), " client da else   ga tushdi ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Clients_Get>, t: Throwable) {

                }
            })
        }

    }
    private fun responsGet(){

        binding.lottiAboutSumma.visibility=View.GONE
        binding.lottiAmountPaid.visibility=View.GONE
        binding.lottiDate.visibility=View.GONE
        binding.lottiDeptSumma.visibility=View.GONE


        binding.amountPaidOrder.visibility=View.VISIBLE
        binding.orderAboutDate.visibility=View.VISIBLE
        binding.orderAboutSumma.visibility=View.VISIBLE
        binding.orderAboutDebt.visibility=View.VISIBLE


        binding.orderName.text=list2[position!!].nom
        binding.orderAboutDebt.text= "${list[position!!].nasiya} so'm "
        binding.orderAboutSumma.text= "${list[position!!].summa} so'm "
        binding.amountPaidOrder.text="${list[position!!].tolanganSumma} so'm "

        val oykunyil=list[position!!].sana.substring(0..9)
        val soatmintutsekund=list[position!!].sana.substring(11..18)
        binding.orderAboutDate.text="$oykunyil||$soatmintutsekund"

    }
}