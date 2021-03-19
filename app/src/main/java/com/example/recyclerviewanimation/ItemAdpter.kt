package com.example.recyclerviewanimation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewanimation.databinding.ItemCardBinding
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(private val dataList: ArrayList<ItemModel>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(), ItemHelperInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    class ViewHolder(private val itemBinding: ItemCardBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(itemModel: ItemModel){
            itemBinding.txtItemNum.text = itemModel.item_num
            itemBinding.txtProductNum.text = itemModel.product_num
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemModel: ItemModel = dataList[position]
        holder.bind(itemModel)
    }

    override fun getItemCount() = dataList.size

    override fun onItemVerticalMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(dataList,fromPosition,toPosition)
        notifyItemMoved(fromPosition,toPosition)
        Log.d("test", "onItemVerticalMove ===")
        Log.d("test", "fromPosition = $fromPosition")
        Log.d("test", "fromPosition = $toPosition")
    }
}