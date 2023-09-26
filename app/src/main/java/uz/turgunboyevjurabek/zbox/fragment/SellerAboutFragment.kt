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
import uz.turgunboyevjurabek.zbox.Seller.getSeller
import uz.turgunboyevjurabek.zbox.databinding.FragmentSellerAboutBinding
import uz.turgunboyevjurabek.zbox.Objekt.markUser
import uz.turgunboyevjurabek.zbox.network.ApiClinet


class SellerAboutFragment : Fragment() {
    private val binding by lazy { FragmentSellerAboutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val id = markUser.id.toInt()
        ApiClinet.getApiServis(requireContext()).getSeller(id)
            .enqueue(object : Callback<getSeller> {
                override fun onResponse(call: Call<getSeller>, response: Response<getSeller>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user!=null){
                            binding.tvId.text=user.id.toString()
                            binding.tvIsm.text=user.ism
                            binding.tvFamilya.text=user.fam
                            binding.tvManzil.text=user.manzil
                            binding.tvTelNomer.text=user.tel_nomer
                            binding.tvUser.text=user.user.toString()
                        }
                        else Toast.makeText(context, "Ma'lumotlar topilmadi", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(context, "Yuklashdagi xatolik", Toast.LENGTH_SHORT).show()
                    }


                }

                override fun onFailure(call: Call<getSeller>, t: Throwable) {
                    Toast.makeText(context, "Ulanishdagi xatolik", Toast.LENGTH_SHORT).show()

                }
            })




        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

}