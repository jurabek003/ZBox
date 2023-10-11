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
import uz.turgunboyevjurabek.zbox.Seller.getSeller
import uz.turgunboyevjurabek.zbox.databinding.FragmentProductAboutBinding
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID
import uz.turgunboyevjurabek.zbox.network.ApiClinet


class ProductAboutFragment : Fragment() {
    private val binding by lazy { FragmentProductAboutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val select_id = markUser.id
        val api = ApiClinet.getApiServis(requireContext()).getProductId(select_id)
            .enqueue(object : Callback<Product_Get_with_ID> {
                override fun onResponse(
                    call: Call<Product_Get_with_ID>,
                    response: Response<Product_Get_with_ID>
                ) {
                    if (response.isSuccessful) {
                        val product = response.body()
                        if (product != null) {
                            binding.productNom.text = product.nom
                            binding.productHajmi.text = product.hajmi
                            binding.productMiqdor.text = product.miqdor.toString()
                            binding.productOlchov.text = product.olchov
                            binding.productNarx.text = product.narx.toString()
                            binding.productSana.text = product.kelganSana
                            binding.writeText.text = "kelgan sana:"

                        }
                    } else {
                        Toast.makeText(context, "Yuklashdagi xatolik", Toast.LENGTH_SHORT).show()

                    }

                }

                override fun onFailure(call: Call<Product_Get_with_ID>, t: Throwable) {
                    Toast.makeText(context, "Ulanishdagi xatolik", Toast.LENGTH_SHORT).show()
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