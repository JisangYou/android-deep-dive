package com.example.memo.memolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo
import com.example.memo.databinding.MemoItemBinding
import com.example.memo.memolist.MemoAdapter.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Adapter for the task list. Has a reference to the [TasksViewModel] to send actions back to it.
 */
class MemoAdapter(private val viewModel: MemoViewModel, val onClickListener: OnClickListener) : ListAdapter<Memo, ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addSubmitList(list: List<Memo>?) {
        adapterScope.launch {

            withContext(Dispatchers.Main) {
                submitList(list)
            }
        }
    }

    class ViewHolder private constructor(private val binding: MemoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MemoViewModel, item: Memo) {

            binding.viewmodel = viewModel
            binding.memo = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemoItemBinding.inflate(layoutInflater, parent, false)

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

    class OnClickListener(val clickListener: (memo: Memo) -> Unit) {
        fun onClick(memo: Memo) = clickListener(memo)
    }
}




