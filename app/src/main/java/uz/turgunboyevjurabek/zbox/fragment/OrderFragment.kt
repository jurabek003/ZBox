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
import uz.turgunboyevjurabek.zbox.Seller.getSeller
import uz.turgunboyevjurabek.zbox.adapter.RvAdapterOrder
import uz.turgunboyevjurabek.zbox.databinding.FragmentOrderBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis


class OrderFragment : Fragment() {
    private lateinit var apiServis: ApiServis
    private lateinit var rvAdapterOrder: RvAdapterOrder
     lateinit var list:ArrayList<Order_get>
     lateinit var list_rv:ArrayList<Int>
     lateinit var list_product:ArrayList<Product_Get_with_ID>
    private var Size:Int?=null
    private var Size2:Int?=null
    private lateinit var list_son: ArrayList<Int>
    private lateinit var list_son2:ArrayList<Int>
    private lateinit var list_seller:ArrayList<getSeller>
    private val binding by lazy { FragmentOrderBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        list_product= ArrayList()
        list = ArrayList()
        list_son= ArrayList()
        list_son2= ArrayList()
        list_rv= ArrayList()
        list_seller= ArrayList()
        
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
                    Toast.makeText(requireContext(), "successful at getOrder ", Toast.LENGTH_SHORT).show()
                    
                    for(i in 0 until list.size){
                        list_rv.add(list[i].mahsulot)
                    }
                    
                    for(i in 0 until list.size){
                        list_son2.add(list[i].sotuvchi)
                    }
                    
                    if (list_rv.isNotEmpty()){
                        getProductID(list_rv)
                        Size=list_rv.size
                    }
                    
                    if (list_son2.isNotEmpty()){
                        getSellerId(list_son2)
                        Size2 = list_son2.size
                    }
                    
                    
                }else{
                    Toast.makeText(requireContext(), "vay elsga tushdi", Toast.LENGTH_SHORT).show()
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Order_get>>, t: Throwable) {
                Toast.makeText(requireContext(), "failure ga tushdi ${t.message}", Toast.LENGTH_SHORT).show()
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


             }
             override fun onFailure(call: Call<Product_Get_with_ID>, t: Throwable) {
                 Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
             }
         }) 
     }
 }
    
    private fun getSellerId(list_son2:ArrayList<Int>){
        apiServis=ApiClinet.getApiServis(requireContext())
        
        for (i in 0 until list_son2.size){
            apiServis.getSeller(list_son2[i]).enqueue(object :Callback<getSeller>{
                override fun onResponse(call: Call<getSeller>, response: Response<getSeller>) {
                    if (response.isSuccessful && response.body()!=null){
                     //   Toast.makeText(requireContext(), "getSeller da hammasi yaxshi ${response.body()}", Toast.LENGTH_SHORT).show(
                        list_seller.add(response.body()!!)

                        if (list_product.isNotEmpty() && list_seller.isNotEmpty() ){
                            Toast.makeText(requireContext(), "list_seller.size ${list_seller.size} Size2 $Size2", Toast.LENGTH_LONG).show()
                            if (list_product.size==Size ){
                                if (list_seller.size==Size2){
                                    rvAdapterOrder=RvAdapterOrder(requireContext(),list,list_product,list_seller)
                                    binding.rvOrder.adapter=rvAdapterOrder
                                    rvAdapterOrder.notifyDataSetChanged()
                                }
                            }

                        }

                    }
                }

                override fun onFailure(call: Call<getSeller>, t: Throwable) {
                    Toast.makeText(requireContext(), "getSeller ni ichida failurega tushdi", Toast.LENGTH_SHORT).show()
                }
            })
        }
        
        
    }
}