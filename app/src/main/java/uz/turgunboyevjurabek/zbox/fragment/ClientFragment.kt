package uz.turgunboyevjurabek.zbox.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.zbox.Objekt.ClientObj
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.adapter.RvClick
import uz.turgunboyevjurabek.zbox.adapter.RvClientAdapter
import uz.turgunboyevjurabek.zbox.databinding.FragmentClientBinding
import uz.turgunboyevjurabek.zbox.databinding.ItemAddClientBinding

import uz.turgunboyevjurabek.zbox.madels.Client_Post_Request
import uz.turgunboyevjurabek.zbox.madels.Clients_Get
import uz.turgunboyevjurabek.zbox.madels.Clients_Post
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis


class ClientFragment : Fragment(),RvClick {
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

        //api clientlarni hammasini olib kelish uchun
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

        // api ga post qilish uchun
        binding.btnFloatingClient.setOnClickListener {
            val itemAddClientBinding= ItemAddClientBinding.inflate(layoutInflater)
            val dialogPost= MaterialAlertDialogBuilder(requireContext()).create()
            dialogPost.setView(itemAddClientBinding.root)
            dialogPost.show()

            itemAddClientBinding.btnSave.setOnClickListener {
                itemAddClientBinding.apply {
                    if (!clientName.text.isNullOrEmpty() && !clientLastName.text.isNullOrEmpty() && !clientManzil.text.isNullOrEmpty() && !clientTel.text.isNullOrEmpty() && !clientSumma.text.isNullOrEmpty()){
                        val clientsPost= Client_Post_Request(clientName.text.toString(),clientLastName.text.toString(),clientManzil.text.toString(),clientTel.text.toString(),clientSumma.text.toString().toInt())
                        apiServis.postClient(clientsPost).enqueue(object :Callback<ArrayList<Clients_Post>>{
                            override fun onResponse(
                                call: Call<ArrayList<Clients_Post>>,
                                response: Response<ArrayList<Clients_Post>>,
                            ) {
                                if (response.isSuccessful && response.body()!=null){
                                    Toast.makeText(requireContext(), "YEsssssssssssssss", Toast.LENGTH_SHORT).show()
                                    dialogPost.cancel()
                                }
                            }

                            override fun onFailure(
                                call: Call<ArrayList<Clients_Post>>,
                                t: Throwable,
                            ) {
                                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

                            }
                        })
                    }
                }

            }

        }


    }
    private fun adapterRv(){
        rvClientAdapter=RvClientAdapter(list,this)
        binding.rvClient.adapter=rvClientAdapter
        rvClientAdapter.notifyDataSetChanged()
    }

    override fun onClick(clientsGet: Clients_Get, position: Int) {
        ClientObj.id=clientsGet.id
    }

}