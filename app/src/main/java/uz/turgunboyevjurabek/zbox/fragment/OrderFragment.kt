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
        list_rv= ArrayList()
        ordersGetFromApi()


        return binding.root
    }

    private fun ordersGetFromApi() {
        apiServis=ApiClinet.getApiServis(requireContext())
        apiServis.getOrders().enqueue(object :Callback<ArrayList<Order_get>>{
            override fun onResponse(
                call: Call<ArrayList<Order_get>>,
                response: Response<ArrayList<Order_get>>,
            ) {
                if (response.isSuccessful && response.body()!= null){
                    list.addAll(response.body()!!)
                    Toast.makeText(requireContext(), "Respons keldi $list", Toast.LENGTH_SHORT).show()
                    if (list_product.isNotEmpty() && list.isNotEmpty()){
                        Toast.makeText(requireContext(), "product list tula", Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until list.size){
                        list_rv.add(list[i].mahsulot)
                    }
                    if (!list_rv.isEmpty()){
                        Toast.makeText(requireContext(), "list_rv tuldi $list_rv", Toast.LENGTH_SHORT).show()
                    }

                    if (list_rv.isNotEmpty()){
                        getProductID(list_rv)
                        ID=list_rv.size
                    }
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
     Toast.makeText(requireContext()
         , "getProductID ichidagi birinchi list_rv $list_rv", Toast.LENGTH_SHORT).show()

     for (i in 0 until list_son.size){
         apiServis.getProductId(list_son[i]).enqueue(object :Callback<Product_Get_with_ID>{
             override fun onResponse(
                 call: Call<Product_Get_with_ID>,
                 response: Response<Product_Get_with_ID>
             ) {
                 list_product.add(response.body()!!)
                 Toast.makeText(requireContext(), "${response.body()}", Toast.LENGTH_SHORT).show()
                 Product_with_ID.list.add(response.body()!!)
                 Toast.makeText(requireContext(), "responsni ichida list_rv = $list_rv", Toast.LENGTH_SHORT).show()
                 Toast.makeText(requireContext(), "responsni ichida list_product = ${list_product.size}", Toast.LENGTH_SHORT).show()

                 if (list_product.isNotEmpty()){

                     if (list_product.size==ID){
                         Toast.makeText(requireContext(), "list_productt ten buldi ID ga", Toast.LENGTH_SHORT).show()
                         rvAdapterOrder=RvAdapterOrder(requireContext(),list,list_product)
                         binding.rvOrder.adapter=rvAdapterOrder
                         rvAdapterOrder.notifyDataSetChanged()
                     }

                 }

             }
             override fun onFailure(call: Call<Product_Get_with_ID>, t: Throwable) {
                 Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
             }
         })

     }


    }


}