package uz.turgunboyevjurabek.zbox.fragment

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.zbox.Objekt.ClientObj
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.databinding.FragmentClientAboutBinding
import uz.turgunboyevjurabek.zbox.databinding.FragmentClientBinding
import uz.turgunboyevjurabek.zbox.databinding.SmsSendDialogBinding
import uz.turgunboyevjurabek.zbox.madels.Clients_Get
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import uz.turgunboyevjurabek.zbox.network.ApiServis

class ClientAboutFragment : Fragment() {
    private  val binding by lazy { FragmentClientAboutBinding.inflate(layoutInflater) }
    private lateinit var apiServis: ApiServis
    private lateinit var phoneNumber:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        // Inflate the layout for this fragment

        apiWorking()
        binding.cardCall.setOnClickListener {
            calling()
        }
        sendSms()

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
                        phoneNumber=response.body()!!.tel
                        Toast.makeText(requireContext(), response.body()!!.tel, Toast.LENGTH_SHORT).show()

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
        askPermission(Manifest.permission.CALL_PHONE){
            try {
                val intent=(Intent(Intent.ACTION_CALL))
                intent.data= Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            }catch (e:UninitializedPropertyAccessException){
                Toast.makeText(requireContext(),"Yuklanmoqda...",Toast.LENGTH_SHORT).show()
            }

        }.onDeclined {e->
if (e.hasDenied()){
    AlertDialog.Builder(requireContext())
        .setMessage("Ruxsat bermasangiz ilova ishlay olmaydi ruxsat bering...")
        .setPositiveButton("yes") { dialog, which ->
            e.askAgain();
        } //ask again
        .setNegativeButton("no") { dialog, which ->
            dialog.dismiss();
        }
        .show();
}
            // nastroykani ochish uchun
            if(e.hasForeverDenied()) {
                e.goToSettings();
            }

        }

    }
    private fun sendSms(){
        binding.cardSms.setOnClickListener {
            val smsSendDialogBinding = SmsSendDialogBinding.inflate(layoutInflater)
            val dialog = BottomSheetDialog(requireContext())
            dialog.setContentView(smsSendDialogBinding.root)
            dialog.show()

            askPermission(android.Manifest.permission.SEND_SMS) {
                try {
                    val matn = smsSendDialogBinding.edtMassage.text
                    val obj = SmsManager.getDefault()
                    smsSendDialogBinding.btnSend.setOnClickListener {
                        // SMS jo'natish uchun PendingIntent tuziladi
                       // val sentPendingIntent = PendingIntent.getBroadcast(requireContext(), 0, Intent("SMS_SENT"), PendingIntent.FLAG_IMMUTABLE)

                        // SMS jo'natish uchun PendingIntent tuziladi
                        //val deliveredPendingIntent = PendingIntent.getBroadcast(requireContext(), 0, Intent("SMS_DELIVERED"), PendingIntent.FLAG_IMMUTABLE)

                        obj.sendTextMessage(phoneNumber, null, matn.toString(), null,null)

                        Toast.makeText(requireContext(), "Send Message 📤 ", Toast.LENGTH_SHORT)
                            .show()
                        dialog.cancel()

                    }

                } catch (e: UninitializedPropertyAccessException) {
                    Toast.makeText(requireContext(), "Yuklanmoqda...", Toast.LENGTH_SHORT).show()
                }

            }.onDeclined { e ->
                if (e.hasDenied()) {
                    AlertDialog.Builder(requireContext())
                        .setMessage("Ruxsat bermasangiz ilova ishlay olmaydi ruxsat bering 🤫...")
                        .setPositiveButton("yes 😉") { dialog, which ->
                            e.askAgain();
                        } //ask again
                        .setNegativeButton("no 😡") { dialog, which ->
                            dialog.dismiss();
                        }
                        .show();
                }
                if (e.hasForeverDenied()) {
                    e.goToSettings();
                }

            }
        }

    }
}