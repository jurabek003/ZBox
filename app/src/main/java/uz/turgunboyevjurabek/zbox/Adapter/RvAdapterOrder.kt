package uz.turgunboyevjurabek.zbox.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.zbox.databinding.ItemOrderRvBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get
import uz.turgunboyevjurabek.zbox.madels.Product.Product_Get_with_ID

class RvAdapterOrder(val context: Context, val list2:ArrayList<Order_get>,val list: ArrayList<Product_Get_with_ID>  ):RecyclerView.Adapter<RvAdapterOrder.Vh>() {
    inner class Vh(val itemOrderRvBinding: ItemOrderRvBinding):ViewHolder(itemOrderRvBinding.root){

        fun onBind(order_get: Order_get,position: Int,productGetWithId: Product_Get_with_ID){

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list2.size

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list2[position],position,list[position])
    }
}