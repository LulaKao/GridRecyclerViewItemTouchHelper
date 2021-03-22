package com.example.recyclerviewanimation

import android.graphics.Color
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewanimation.databinding.ItemCardBinding
import kotlin.collections.ArrayList

class ItemAdapter(private val dataList: ArrayList<ItemModel>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(), ItemHelperInterface {
    var index : Int = 0 // 記錄 index 焦點

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    class ViewHolder(private val itemBinding: ItemCardBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(itemModel: ItemModel){
            // set data
            itemBinding.txtItemNum.text = itemModel.item_num
            itemBinding.txtProductNum.text = itemModel.product_num
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("PPP", "onBindViewHolder position = $position")
        Log.d("PPP", "onBindViewHolder index = $index")

        // bind data
        val itemModel: ItemModel = dataList[position]
        holder.bind(itemModel)

        // set itemView focusable
        holder.itemView.isFocusable = true

        // set onFocusChangeListener
        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(hasFocus) holder.itemView.setBackgroundColor(Color.GRAY)
            else holder.itemView.setBackgroundColor(Color.parseColor("#393D44"))
            Log.d("PPP", "onFocusChangeListener $hasFocus,$position")
        }

        // set onKeyListener
        holder.itemView.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(event?.action!= null){
                    if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN){ // 按下遙控器下鍵
//                        onItemVerticalMove(position,position + 5) // 執行垂直移動的動畫
//                        index = position + 5 // 記錄新的 index

                        onItemVerticalMove(holder.adapterPosition,holder.adapterPosition + 5) // 執行垂直移動的動畫
                        index = holder.adapterPosition // 記錄新的 index

                        Log.d("PPP","position = $position")
                        Log.d("PPP","index = $index")
                        Log.d("PPP","holder.adapterPosition = ${holder.adapterPosition}")

//                        // handler 等一秒再做，才可以看到移動的動畫
//                        Handler().postDelayed({
//                            notifyDataSetChanged() // 移動完要刷新資料
//                        },1000)

                        return true
                    } else if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN){ // 按下遙控器上鍵
//                        onItemVerticalMove(position,position - 5) // 執行垂直移動的動畫
//                        index = position - 5 // 記錄新的 index

                        onItemVerticalMove(holder.adapterPosition,holder.adapterPosition - 5) // 執行垂直移動的動畫
                        index = holder.adapterPosition // 記錄新的 index

                        Log.d("PPP","position = $position")
                        Log.d("PPP","index = $index")
                        Log.d("PPP","holder.adapterPosition = ${holder.adapterPosition}")

//                        // handler 等一秒再做，才可以看到移動的動畫
//                        Handler().postDelayed({
//                            notifyDataSetChanged() // 移動完要刷新資料
//                        },1000)

                        return true
                    }
                }
                return false
            }
        })

        if(position == index) { // 只有一個焦點
            holder.itemView.requestFocus() // 請求焦點
        }
    }

    override fun getItemCount() = dataList.size

    // 執行垂直移動的動畫
    override fun onItemVerticalMove(fromPosition: Int, toPosition: Int) {
        var itemSelected = dataList[fromPosition] // 取得被選中的 item
        dataList.removeAt(fromPosition) // 移除被選中的 item
        dataList.add(toPosition, itemSelected) // 把被選中的 item 加到 toPosition
        notifyItemMoved(fromPosition,toPosition) // 移動被選中的 item（帶有動畫效果）
        Log.d("test", "onItemVerticalMove ===")
        Log.d("test", "fromPosition = $fromPosition")
        Log.d("test", "fromPosition = $toPosition")

//        Collections.swap(dataList,fromPosition,toPosition) // 這是兩個 item 互相交換
    }
}