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
import uz.turgunboyevjurabek.zbox.Objekt.Product_with_ID
import uz.turgunboyevjurabek.zbox.adapter.RvAdapterOrder
import uz.turgunboyevjurabek.zbox.databinding.FragmentOrderBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product.Product
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis


class OrderFragment : Fragment() {
    private lateinit var apiServis: ApiServis
    private lateinit var rvAdapterOrder: RvAdapterOrder
     lateinit var list:ArrayList<Order_get>
     lateinit var list_rv:ArrayList<Int>
     lateinit var list_product:ArrayList<Product_Get_with_ID>
    private var ID:Int?=null
    private lateinit var list_son: ArrayList<Int>
    private val binding by lazy { FragmentOrderBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        list_product= ArrayList()
        list = ArrayList()
        list_son= ArrayList()

        ordersGetFromApi()


        return binding.root
    }

    private fun ordersGetFromApi() {
        apiServis=ApiClinet.getApiServis(requireContext())
        list_rv= ArrayList()
        apiServis.getOrders().enqueue(object :Callback<ArrayList<Order_get>>{
            override fun onResponse(
                call: Call<ArrayList<Order_get>>,
                response: Response<ArrayList<Order_get>>,
            ) {
                if (response.isSuccessful && response.body()!= null){
                    list.addAll(response.body()!!)
                    rvAdapterOrder=RvAdapterOrder(requireContext(),list)
                    binding.rvOrder.adapter=rvAdapterOrder

                }else{
                    Toast.makeText(requireContext(), "vay elsga tushdi", Toast.LENGTH_SHORT).show()
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Order_get>>, t: Throwable) {
                Toast.makeText(requireContext(), "failure ga tushdi", Toast.LENGTH_SHORT).show()
            }

        })

    }

 private fun getProductID(list_son:ArrayList<Int>){

        apiServis=ApiClinet.getApiServis(requireContext())

     for (i in 0 until list_son.size){
         apiServis.getProductId(list_son[i]).enqueue(object :Callback<Product_Get_with_ID>{
             override fun onResponse(
                 call: Call<Product_Get_with_ID>,
                 response: Response<Product_Get_with_ID>
             ) {
                 list_product.add(response.body()!!)
                 Toast.makeText(requireContext(), "${response.body()}", Toast.LENGTH_SHORT).show()
                 Product_with_ID.list.add(response.body()!!)
             }
             override fun onFailure(call: Call<Product_Get_with_ID>, t: Throwable) {
                 Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
             }
         })

     }


    }


}