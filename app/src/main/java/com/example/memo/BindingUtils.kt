package com.example.memo

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo
import com.example.memo.memolist.MemoAdapter

@BindingAdapter("memo_adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("app:memo_items")
fun setItems(listView: RecyclerView, items: List<Memo>?) {
    items?.let {
        (listView.adapter as MemoAdapter).submitList(items)
    }
}