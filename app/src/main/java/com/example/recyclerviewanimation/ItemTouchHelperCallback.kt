package com.example.recyclerviewanimation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(var mAdapter: ItemHelperInterface?) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // 設定可往上或下拖曳
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        // 設定可往左或右滑動
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        return  makeMovementFlags(dragFlags, swipeFlags)
    }

    // 啟用長按可拖動
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    // 啟用 ItemView 可滑動
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    // 上下拖曳監聽事件
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // 呼叫 ItemHelperInterface 的方法
        mAdapter!!.onItemVerticalMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    // 左右滑動監聽事件
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        return
    }
}