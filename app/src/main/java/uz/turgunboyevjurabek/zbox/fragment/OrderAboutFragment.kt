package uz.turgunboyevjurabek.zbox.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.turgunboyevjurabek.zbox.R
import uz.turgunboyevjurabek.zbox.databinding.FragmentOrderAboutBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID

class OrderAboutFragment : Fragment() {
    private val binding by lazy { FragmentOrderAboutBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<Order_get>
    private lateinit var list2:ArrayList<Product_Get_with_ID>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        responseSelect()
        return binding.root
    }

    private fun responseSelect() {

        list= ArrayList()
        list2= ArrayList()
        list=arguments?.getStringArrayList("keyOrder")  as ArrayList<Order_get>
        list2=arguments?.getStringArrayList("keyProduct")  as ArrayList<Product_Get_with_ID>
        val position=arguments?.getInt("keyPosition")
        binding.orderName.text=list2[position!!].nom
        binding.orderAboutDebt.text= "${list[position].nasiya} so'm "
        binding.orderAboutSumma.text= "${list[position].summa} so'm "
        val oykunyil=list[position].sana.substring(0..9)
        val soatmintutsekund=list[position].sana.substring(11..18)
        binding.orderAboutDate.text="$oykunyil||$soatmintutsekund"

    }
}