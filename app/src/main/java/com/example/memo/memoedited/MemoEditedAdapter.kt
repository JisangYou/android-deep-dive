package com.example.memo.memoedited

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo
import com.example.memo.databinding.MemoImageItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoEditedAdapter(private val viewModel: MemoEditedViewModel, val onClickListener: OnClickListener) :
    ListAdapter<Memo, MemoEditedAdapter.ViewHolder>(DiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    fun addSubmitList(list: List<Memo>?) {
        adapterScope.launch {

            withContext(Dispatchers.Main) {
                submitList(list)
            }
        }
    }

    class ViewHolder private constructor(private val binding: MemoImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MemoEditedViewModel, item: Memo) {

            binding.viewmodel = viewModel
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

    companion object DiffCallback : DiffUtil.ItemCallback<Memo>() {
        override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem.memoId == newItem.memoId
        }
    }

    class OnClickListener(val clickListener: (memoId: Long) -> Unit) {
        fun onClick(memo: Memo) = clickListener(memo.memoId)
    }
}