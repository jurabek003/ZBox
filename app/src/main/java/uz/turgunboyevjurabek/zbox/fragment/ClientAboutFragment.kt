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
import uz.turgunboyevjurabek.zbox.Objekt.ClientObj
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.databinding.FragmentClientAboutBinding
import uz.turgunboyevjurabek.zbox.databinding.FragmentClientBinding
import uz.turgunboyevjurabek.zbox.madels.Clients_Get
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis

class ClientAboutFragment : Fragment() {
    private  val binding by lazy { FragmentClientAboutBinding.inflate(layoutInflater) }
    private lateinit var apiServis: ApiServis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        // Inflate the layout for this fragment

        apiWorking()
        calling()
        return binding.root
    }
    private fun apiWorking() {
        val idNumber = ClientObj.id
        if (idNumber != null) {

            apiServis=ApiClinet.getApiServis(requireContext())
            apiServis.getClientId(idNumber).enqueue(object :Callback<Clients_Get>{
                override fun onResponse(call: Call<Clients_Get>, response: Response<Clients_Get>) {
                    if (response.isSuccessful && response.body()!=null){

                        binding.clientName.text= response.body()!!.ism
                        binding.clientLastName.text=response.body()!!.fam

                    }else{
                        Toast.makeText(requireContext(), "elsga tushdi ", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<Clients_Get>, t: Throwable) {
                    Toast.makeText(requireContext(), "Muvofaqqiyatsiz 🤢", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    private fun calling() {
        val phoneNumber=ClientObj
    }
}