package com.example.recyclerview

import androidx.recyclerview.widget.DiffUtil

// DiffUtil - 서로 다른 아이템인지를 체크하여 달라진 아이템만 갱신을 도와주는 Util
class DiffUtilCallback(
    private val oldItems: List<ItemData>,
    private val newItems: List<ItemData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        // 바뀌 기 전 리스트의 크기를 리턴
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        // 바뀐 후 리스트의 크기를 리턴
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // 두 객체가 동일한 항목을 나타내는지 확인
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // 두 항목의 데이터가 같은지 확인
        // areItemsTheSame 이 true일 때만 불
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}