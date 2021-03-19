package com.example.recyclerviewanimation

interface ItemHelperInterface {
    fun onItemVerticalMove(fromPosition:Int, toPosition:Int) // 處理上下拖曳來產生換位效果
}