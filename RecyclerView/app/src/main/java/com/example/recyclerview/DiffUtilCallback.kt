package com.example.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldTiles: List<ItemData>,
    private val newTiles: List<ItemData>
) : DiffUtil.ItemCallback<ItemData>() {

    override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        TODO("Not yet implemented")
    }
}