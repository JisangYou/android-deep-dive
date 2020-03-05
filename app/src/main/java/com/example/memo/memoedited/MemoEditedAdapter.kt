package com.example.memo.memoedited

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memo.R
import com.example.memo.data.Memo
import com.example.memo.databinding.MemoImageItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoEditedAdapter(
    private val viewModel: MemoEditedViewModel,
    val onClickListener: OnClickListener
) :
    ListAdapter<String, MemoEditedAdapter.ViewHolder>(DiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Log.e(TAG, "onBindViewHolder$item");
        holder.bind(viewModel, item)
    }

    fun addSubmitList(list: List<String>?) {
        adapterScope.launch {

            withContext(Dispatchers.Main) {
                Log.e(TAG, "addSubmitList jayyyy");
                submitList(list)
            }
        }
    }

    class ViewHolder private constructor(private val binding: MemoImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MemoEditedViewModel, item: String) {

            binding.viewmodel = viewModel
            Glide.with(binding.root)
                .load(item)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_background))
                .into(binding.ivImage)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemoImageItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (memoId: Long) -> Unit) {
        fun onClick(memo: Memo) = clickListener(memo.memoId)
    }
}