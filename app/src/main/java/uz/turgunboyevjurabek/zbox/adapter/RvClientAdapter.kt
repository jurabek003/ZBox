package uz.turgunboyevjurabek.zbox.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.zbox.databinding.ItemRvClientBinding
import uz.turgunboyevjurabek.zbox.madels.Clients_Get


class RvClientAdapter(val list: ArrayList<Clients_Get>) :
        RecyclerView.Adapter<RvClientAdapter.Vh>() {
        inner class Vh(val itemrv: ItemRvClientBinding) : RecyclerView.ViewHolder(itemrv.root) {
            fun onBind(clientsGet: Clients_Get) {
                itemrv.itemName.text=clientsGet.ism
                itemrv.itemLastName.text=clientsGet.fam
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
            return Vh(ItemRvClientBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: Vh, position: Int) {
            holder.onBind(list[position])
        }

    }
