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
import uz.turgunboyevjurabek.zbox.Adapter.AdapterSeller
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.Seller.getSellers
import uz.turgunboyevjurabek.zbox.databinding.FragmentSellerBinding
import uz.turgunboyevjurabek.zbox.Objekt.markUser
import uz.turgunboyevjurabek.zbox.network.ApiClinet

class SellerFragment : Fragment() {
    private val binding by lazy { FragmentSellerBinding.inflate(layoutInflater) }
    private lateinit var adapterSeller: AdapterSeller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        ApiClinet.getApiServis(requireContext()).getSellers()
            .enqueue(object : Callback<ArrayList<getSellers>> {
                override fun onResponse(
                    call: Call<ArrayList<getSellers>>,
                    response: Response<ArrayList<getSellers>>
                ) {
                    if (response.isSuccessful) {
                        adapterSeller =
                            AdapterSeller(response.body(), object : AdapterSeller.rvAction {
                                override fun OnClick(
                                    arraylist: ArrayList<getSellers>,
                                    position: Int
                                ) {
                                    markUser.id = response.body()!![position].id
                                    findNavController().navigate(R.id.sellerAboutFragment)
                                }
                            })

                        binding.productRv.adapter = adapterSeller
                    } else {
                        Toast.makeText(context, "Yuklashdagi xatolik", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ArrayList<getSellers>>, t: Throwable) {
                    Toast.makeText(context, "Ulanishdagi xatolik", Toast.LENGTH_SHORT).show()

                }
            })

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

}


