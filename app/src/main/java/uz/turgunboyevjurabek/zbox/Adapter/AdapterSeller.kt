package uz.turgunboyevjurabek.zbox.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.zbox.Seller.getSellers
import uz.turgunboyevjurabek.zbox.databinding.ItemSelllerAdapterBinding



class AdapterSeller(val arraylist: ArrayList<getSellers>?,val rvClick: AdapterSeller.rvAction): RecyclerView.Adapter<AdapterSeller.vh>() {
    inner class vh(val itemRvBinding: ItemSelllerAdapterBinding):RecyclerView.ViewHolder(itemRvBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(ItemSelllerAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        val list= arraylist!![position]
        holder.itemRvBinding.tvName.text=list.ism+" "+list.fam
        holder.itemRvBinding.tvNumber.text=list.manzil
        holder.itemRvBinding.btnSellerAbout.setOnClickListener {
            rvClick.OnClick(arraylist,position)
        }

    }


    override fun getItemCount(): Int = arraylist!!.size

    interface rvAction{
        fun OnClick(arraylist: ArrayList<getSellers> ,position: Int)
    }

}