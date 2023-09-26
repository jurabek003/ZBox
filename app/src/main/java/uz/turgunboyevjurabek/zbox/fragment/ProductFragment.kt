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
import uz.turgunboyevjurabek.zbox.Objekt.markUser
import uz.turgunboyevjurabek.zbox.databinding.FragmentProductBinding
import uz.turgunboyevjurabek.zbox.databinding.ItemProductAdapterBinding
import uz.turgunboyevjurabek.zbox.madels.Product

import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis

class ProductFragment : Fragment() {
    private lateinit var adapter: AdapterProduct
    private lateinit var  arrayList:ArrayList<Product>
  private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        ApiClinet.getApiServis(requireContext()).getProduct().enqueue(object:Callback<ArrayList<Product>>{
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                if (response.isSuccessful) {
                    adapter= AdapterProduct(response.body(),object:AdapterProduct.rvAction{
                        override fun OnClick(arraylist: ArrayList<Product>, position: Int) {
                            markUser.id=arraylist[position].id

                        }
                    })
                    binding.productRv.adapter=adapter


                } else {
                    Toast.makeText(context, "Yuklashdagi xatolik", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Toast.makeText(context, "Ulanishdagi xatolik", Toast.LENGTH_SHORT).show()
            }

        })

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }




        // Inflate the layout for this fragment
        return binding.root
    }

}