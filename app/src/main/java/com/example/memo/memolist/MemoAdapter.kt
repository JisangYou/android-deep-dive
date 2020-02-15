package com.example.memo.memolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo
import com.example.memo.databinding.MemoItemBinding
import com.example.memo.memolist.MemoAdapter.ViewHolder

/**
 * Adapter for the task list. Has a reference to the [TasksViewModel] to send actions back to it.
 */
class MemoAdapter(private val viewModel: MemoViewModel) :
    ListAdapter<Memo, ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun submitList(list: MutableList<Memo>?) {
        super.submitList(list)
    }

    override fun getItem(position: Int): Memo {
        return super.getItem(position)
    }

    override fun getItemCount(): Int {
        return 3
    }

    class ViewHolder private constructor(val binding: MemoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem.memoId== newItem.memoId
    }

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem == newItem
    }
}
