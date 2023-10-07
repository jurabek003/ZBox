package uz.turgunboyevjurabek.zbox.fragment

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
import uz.turgunboyevjurabek.zbox.Objekt.markUser
import uz.turgunboyevjurabek.zbox.databinding.FragmentProductAboutBinding
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID
import uz.turgunboyevjurabek.zbox.network.ApiClinet


class ProductAboutFragment : Fragment() {
    private val binding by lazy { FragmentProductAboutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(requireContext(), "${markUser.id}", Toast.LENGTH_SHORT).show()

       val apiServis= ApiClinet.getApiServis(requireContext())
        apiServis.getProductId(markUser.id).enqueue(object :Callback<Product_Get_with_ID>{
            override fun onResponse(
                call: Call<Product_Get_with_ID>,
                response: Response<Product_Get_with_ID>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    Toast.makeText(requireContext(), "UrAAAAAAAAAA", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(requireContext(), "else tushdi", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Product_Get_with_ID>, t: Throwable) {
                Toast.makeText(requireContext(), "onFailure tushdi ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("hole", t.message.toString())
            }
        })
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        return binding.root
    }

}