package com.example.recyclerviewanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerviewanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var itemList = arrayListOf<ItemModel>()
//    private val mViewModel by viewModels<>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // view binding
        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // set data
        for(i in 0..14){
            itemList.add(ItemModel(i.toString(),"2330"))
        }

        // init recyclerView
        var recyclerView = binding.recyclerView

        // init adapter
        var itemAdapter = ItemAdapter(itemList)

        // set layoutManager
        recyclerView.layoutManager = GridLayoutManager(this,5)
//        recyclerView.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }

        // set adapter
        recyclerView.adapter = itemAdapter

        // set touch helper callback
        val callback = ItemTouchHelperCallback(itemAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }
}