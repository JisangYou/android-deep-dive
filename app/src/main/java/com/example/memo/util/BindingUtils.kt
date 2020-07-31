package com.example.memo.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memo.R
import com.example.memo.data.model.db.Memo
import com.example.memo.ui.edited.MemoEditedAdapter
import com.example.memo.ui.list.MemoAdapter


@BindingAdapter("memo_items")
fun setMemoItems(listView: RecyclerView, items: List<Memo>?) {
    items?.let {
        (listView.adapter as MemoAdapter).submitList(items)
    }
}

@BindingAdapter("img_items")
fun setImageItems(recyclerView: RecyclerView, data: List<String>?) {
    val adapter = recyclerView.adapter as MemoEditedAdapter
    adapter.submitList(data)
}

@BindingAdapter("img_url")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(imgView)
    }
}

