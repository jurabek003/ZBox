package uz.turgunboyevjurabek.zbox.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.zbox.databinding.ItemOrderRvBinding
import uz.turgunboyevjurabek.zbox.madels.Order.Order_get

import uz.turgunboyevjurabek.zbox.madels.Product

class RvAdapterOrder(val list:ArrayList<Product>, val list2:ArrayList<Order_get>):RecyclerView.Adapter<RvAdapterOrder.Vh>() {
    inner class Vh(val itemOrderRvBinding: ItemOrderRvBinding):ViewHolder(itemOrderRvBinding.root){
        fun onBind(order_get: Order_get,product: Product){
            itemOrderRvBinding.itemOrderName.text= product.nom.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list2[position],list[position])
    }
}