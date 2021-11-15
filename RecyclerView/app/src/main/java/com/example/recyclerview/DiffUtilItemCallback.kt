package com.example.recyclerview

import androidx.recyclerview.widget.DiffUtil


class DiffUtilItemCallback : DiffUtil.ItemCallback<ItemData>() {

    override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem == newItem
    }
}