package uz.turgunboyevjurabek.zbox.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.zbox.Adapter.AdapterProduct
import uz.turgunboyevjurabek.zbox.databinding.FragmentProductBinding
import uz.turgunboyevjurabek.zbox.databinding.ItemProductAdapterBinding
import uz.turgunboyevjurabek.zbox.models.Product
import uz.turgunboyevjurabek.zbox.network.ApiClinet

class ProductFragment : Fragment() {
    private lateinit var adapterBinding: ItemProductAdapterBinding
  private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        context?.let { ApiClinet.getApiServis(it).getProduct().enqueue(object :Callback<ArrayList<Product>>{
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) = if (response.isSuccessful){
                ApiClinet.getApiServis(context!!).getProduct().enqueue(object :Callback<ArrayList<Product>>{
                    override fun onResponse(
                        call: Call<ArrayList<Product>>,
                        response: Response<ArrayList<Product>>
                    ) {
                        if (response.isSuccessful){
                            binding.productRv.adapter=AdapterProduct(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
            else{
                Toast.makeText(context, "Yuklashdagi xatolik", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Toast.makeText(context, "Ulanishdagi xatolik", Toast.LENGTH_SHORT).show()
            }

        }) }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }




        // Inflate the layout for this fragment
        return binding.root
    }

}