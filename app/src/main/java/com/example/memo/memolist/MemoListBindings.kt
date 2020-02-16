package com.example.memo.memolist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Memo>?) {
    items?.let {
        (listView.adapter as MemoAdapter).submitList(items)
    }
}