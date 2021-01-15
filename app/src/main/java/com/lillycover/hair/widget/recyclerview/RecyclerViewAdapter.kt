package com.lillycover.hair.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewViewHolder>() {

    private val itemList = mutableListOf<RecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        getItem(position).bind(holder.binding)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].layoutId
    }

    fun updateItem(item: List<RecyclerViewItem>) {
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): RecyclerViewItem {
        return itemList[position]
    }
}