package com.example.recyclerview

import androidx.recyclerview.widget.DiffUtil

// DiffUtil - 서로 다른 아이템인지를 체크하여 달라진 아이템만 갱신을 도와주는 Util
class DiffUtilCallback(
    private val oldItems: List<ItemData>,
    private val newItems: List<ItemData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}