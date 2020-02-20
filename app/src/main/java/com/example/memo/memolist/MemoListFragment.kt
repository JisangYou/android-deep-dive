package com.example.memo.memolist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.R
import com.example.memo.ViewModelFactory
import com.example.memo.data.Memo
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
            inflater, R.layout.fragment_memo_list, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = MemoDatabase.getInstance(application).memoDao
        val viewModelFactory =
            ViewModelFactory(dataSource, application)

        val memoViewModel =
            ViewModelProvider(this, viewModelFactory).get(MemoViewModel::class.java)
        binding.rvMemoList.layoutManager = LinearLayoutManager(activity)

        binding.memoViewModel = memoViewModel

        binding.rvMemoList.adapter =
            MemoAdapter(memoViewModel, MemoAdapter.OnClickListener { memoId ->
                //                navigateToMemoDetail(memoId)

            })

        binding.lifecycleOwner = this

        memoViewModel.allMemos.observe(viewLifecycleOwner, Observer {
            it?.let {
                (binding.rvMemoList.adapter as MemoAdapter).addSubmitList(it)
            }
        })

        binding.fabAddMemo.setOnClickListener {
            navigateToMemoEdited()

        }

        return binding.root
    }


    private fun navigateToMemoEdited() {
        val action = MemoListFragmentDirections
            .actionFragmentMemoListToMemoEdited()
        findNavController().navigate(action)
    }

    private fun navigateToMemoDetail(memoId: Long) {
        val action = MemoListFragmentDirections
            .actionFragmentMemoListToMemoDetailFragment(memoId)
        findNavController().navigate(action)
    }

}
