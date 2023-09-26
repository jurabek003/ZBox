package uz.turgunboyevjurabek.zbox.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.zbox.Seller.getSellers
import uz.turgunboyevjurabek.zbox.databinding.ItemProductAdapterBinding

import uz.turgunboyevjurabek.zbox.madels.Product


class AdapterProduct(val arraylist: ArrayList<Product>?): RecyclerView.Adapter<AdapterProduct.vh>() {
    inner class vh(val itemRvBinding: ItemProductAdapterBinding):RecyclerView.ViewHolder(itemRvBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(ItemProductAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = arraylist!!.size

    override fun onBindViewHolder(holder: vh, position: Int) {
        val list= arraylist!![position]
        holder.itemRvBinding.tvName.text=list.nom
        holder.itemRvBinding.tvNumber.text=list.narx.toString()
        holder.itemRvBinding.tvId.text=list.id.toString()
        holder.itemRvBinding.tvDate.text=list.kelgan_sana


    }
    interface rvAction{
        fun OnClick(arraylist: ArrayList<Product>)
    }

}