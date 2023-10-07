package uz.turgunboyevjurabek.zbox.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.adapter.RvAdapterOrder
import uz.turgunboyevjurabek.zbox.databinding.FragmentOrderBinding
import uz.turgunboyevjurabek.zbox.madels.Order.OrderForRv
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis
import java.util.Arrays


class OrderFragment : Fragment() {
    private lateinit var apiServis: ApiServis
    private lateinit var rvAdapterOrder: RvAdapterOrder
     lateinit var list:ArrayList<Order_get>
     lateinit var list_rv:ArrayList<Int>
     lateinit var list_product:ArrayList<Product>
    private var ID:Int?=null

    private val binding by lazy { FragmentOrderBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ordersGetFromApi()

        getProductID(5)

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
                    list = ArrayList()
                    list.addAll(response.body()!!)
                    rvAdapterOrder=RvAdapterOrder(context!!,list)
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

 private fun getProductID(id:Int){
        list_product= ArrayList()
        apiServis=ApiClinet.getApiServis(requireContext())
        apiServis.getProductId(id).enqueue(object :Callback<ArrayList<Product>>{
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>,
            ) {
                list_product.addAll(response.body()!!)
                Toast.makeText(requireContext(), "${response.body()}", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }
        })

    }


}