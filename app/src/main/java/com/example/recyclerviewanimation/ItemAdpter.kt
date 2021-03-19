package com.example.recyclerviewanimation

import android.graphics.Color
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewanimation.databinding.ItemCardBinding
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(private val dataList: ArrayList<ItemModel>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(), ItemHelperInterface {
    var index : Int = 0

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
        Log.d("PPP", "onBindViewHolder $position")
        val itemModel: ItemModel = dataList[position]
        holder.bind(itemModel)
        holder.itemView.isFocusable = true
        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(hasFocus) holder.itemView.setBackgroundColor(Color.YELLOW)
            else holder.itemView.setBackgroundColor(Color.GRAY)
            Log.d("PPP", "onFocusChangeListener $hasFocus,$position")
        }
        holder.itemView.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(event?.action!= null){
                    if(event.action ==KeyEvent.ACTION_DOWN && keyCode ==KeyEvent.KEYCODE_DPAD_DOWN){
                        onItemVerticalMove(position,position+5)
                        index = position+5
                        notifyDataSetChanged() // handler 等一秒再做，看可否看到動畫
                        return true
                    } else if(event.action ==KeyEvent.ACTION_DOWN && keyCode ==KeyEvent.KEYCODE_DPAD_UP){
                        onItemVerticalMove(position,position-5)
                        index = position-5
                        notifyDataSetChanged() // handler 等一秒再做，看可否看到動畫
                        return true
                    }
                }
                return false
            }
        })
        if(index == position) holder.itemView.requestFocus() // 只有一個 focus
    }

    override fun getItemCount() = dataList.size

    override fun onItemVerticalMove(fromPosition: Int, toPosition: Int) {
//        var itemSelected = dataList[fromPosition]
//        dataList.removeAt(fromPosition)
//        dataList.add(toPosition, itemSelected)
        Collections.swap(dataList,fromPosition,toPosition)
        notifyItemMoved(fromPosition,toPosition)
        Log.d("test", "onItemVerticalMove ===")
        Log.d("test", "fromPosition = $fromPosition")
        Log.d("test", "fromPosition = $toPosition")
    }
}