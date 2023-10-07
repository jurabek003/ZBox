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
import uz.turgunboyevjurabek.zbox.Objekt.markUser
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.databinding.FragmentProductAboutBinding
import uz.turgunboyevjurabek.zbox.madels.Product
import uz.turgunboyevjurabek.zbox.network.ApiClinet


class ProductAboutFragment : Fragment() {
    private val binding by lazy { FragmentProductAboutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
       val apiServis= ApiClinet.getApiServis(requireContext())
        apiServis.getProductId(markUser.id).enqueue(object :Callback<ArrayList<Product>>{
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>,
            ) {
                if (response.isSuccessful && response.body()!= null){
                    Toast.makeText(requireContext(), "UrAAAAAAAAAA", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "else tushdi", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Toast.makeText(requireContext(), "onFailure tushdi ${t.message}", Toast.LENGTH_SHORT).show()
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