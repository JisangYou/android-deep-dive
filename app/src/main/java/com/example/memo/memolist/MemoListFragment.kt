package com.example.memo.memolist


import android.os.Bundle
import android.util.Log
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_memo_list, container, false)

        val binding: FragmentMemoListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_memo_list, container, false)
//        val application = requireNotNull(this.activity).application
//        val memoDatabaseDao = MemoDatabase.getInstance(application).memoDatabaseDao
//        val viewModelFactory = MemoViewModelFactory(memoDatabaseDao, application)
//        val memoViewModel =
//            ViewModelProviders.of(this, viewModelFactory).get(MemoViewModel::class.java)
        binding.lifecycleOwner = this.viewLifecycleOwner

        val memoViewModel = binding.memoViewModel
        var listAdapter: MemoAdapter
        if (memoViewModel != null) {
            listAdapter = MemoAdapter(memoViewModel)
            binding.rvMemoList.adapter = listAdapter
        } else {
            Log.e("check", "hello")
        }

        return binding.root
    }
}
