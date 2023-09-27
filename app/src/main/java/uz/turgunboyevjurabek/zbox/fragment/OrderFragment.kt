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
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.databinding.FragmentOrderBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis


class OrderFragment : Fragment() {
    private lateinit var apiServis: ApiServis
    private val binding by lazy { FragmentOrderBinding.inflate(layoutInflater)}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
                    Toast.makeText(requireContext(), "Keldi ura", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "vay elsga tushdi", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ArrayList<Order_get>>, t: Throwable) {
                Toast.makeText(requireContext(), "failure ga tushdi", Toast.LENGTH_SHORT).show()
            }

        })
    }

}