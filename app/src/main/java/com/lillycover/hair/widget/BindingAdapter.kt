package com.lillycover.hair.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lillycover.hair.widget.recyclerview.RecyclerViewAdapter
import com.lillycover.hair.widget.recyclerview.RecyclerViewItem

@BindingAdapter("item")
fun RecyclerView.setRecyclerViewItem(item: List<RecyclerViewItem>?) {
    if (adapter == null) {
        adapter = RecyclerViewAdapter()
        layoutManager = LinearLayoutManager(context)
    }
    item?.let { (adapter as? RecyclerViewAdapter)?.updateItem(item) }
}