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
import uz.turgunboyevjurabek.zbox.adapter.RvClientAdapter
import uz.turgunboyevjurabek.zbox.databinding.FragmentClientBinding
import uz.turgunboyevjurabek.zbox.madels.Clients_Get
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis


class ClientFragment : Fragment() {
    private lateinit var apiServis: ApiServis
    private lateinit var list: ArrayList<Clients_Get>
    private lateinit var rvClientAdapter: RvClientAdapter
    private val binding by lazy { FragmentClientBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

       apiWorking()

        return binding.root
    }

    private fun apiWorking() {
        apiServis=ApiClinet.getApiServis(requireContext())
        apiServis.getClients().enqueue(object :Callback<ArrayList<Clients_Get>>{
            override fun onResponse(
                call: Call<ArrayList<Clients_Get>>,
                response: Response<ArrayList<Clients_Get>>,
            ) {
                if (response.isSuccessful && response.body()!=null){

                    list= ArrayList()
                    list.addAll(response.body()!!)
                    adapterRv()
                }
            }

            override fun onFailure(call: Call<ArrayList<Clients_Get>>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun adapterRv(){
        rvClientAdapter=RvClientAdapter(list)
        binding.rvClient.adapter=rvClientAdapter
        rvClientAdapter.notifyDataSetChanged()
    }
}