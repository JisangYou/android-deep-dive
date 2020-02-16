package com.example.memo.memolist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memo.R
import com.example.memo.data.MemoDatabase

import com.example.memo.databinding.FragmentMemoListBinding

/**
 * A simple [Fragment] subclass.
 */
class MemoListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMemoListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_memo_list, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = MemoDatabase.getInstance(application).memoDao
        val viewModelFactory = MemoViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MemoViewModel::class.java)

        binding.memoViewModel = viewModel
        val manager = GridLayoutManager(activity, 3)
        binding.rvMemoList.layoutManager = manager

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (position) {
                0 -> 3
                else -> 1
            }
        }
        val adapter = MemoAdapter(viewModel)
        binding.rvMemoList.adapter = adapter
        
        binding.lifecycleOwner = this
        return binding.root
    }

}
