package uz.turgunboyevjurabek.zbox.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import retrofit2.Call
import retrofit2.Response
import uz.turgunboyevjurabek.zbox.Seller.getSeller
import uz.turgunboyevjurabek.zbox.databinding.ItemOrderRvBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID
import uz.turgunboyevjurabek.zbox.network.ApiClinet
import javax.security.auth.callback.Callback

class RvAdapterOrder(val context: Context, val list2:ArrayList<Order_get>?,val list1:ArrayList<Product_Get_with_ID>?,val itemClick: ItemClick ):RecyclerView.Adapter<RvAdapterOrder.Vh>() {
    inner class Vh(val itemOrderRvBinding: ItemOrderRvBinding):ViewHolder(itemOrderRvBinding.root){

        fun onBind(order_get: Order_get,position: Int,productGetWithId: Product_Get_with_ID){

            itemOrderRvBinding.itemOrderName.text=productGetWithId.nom
            itemOrderRvBinding.itemOrderSana.text=order_get.sana

            itemOrderRvBinding.root.setOnClickListener {
                itemClick.itemClick(list2!!,list1!!,position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list2!!.size

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list2!![position],position, list1!![position])
    }
    interface ItemClick{
        fun itemClick(list:ArrayList<Order_get>, list2:ArrayList<Product_Get_with_ID>,position: Int)
    }

}