package com.example.memo.memolist


import android.os.Bundle
import android.util.Log
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


    private lateinit var binding: FragmentMemoListBinding
    private val TAG = javaClass.simpleName
    private lateinit var memoViewModel: MemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_memo_list, container, false
        )
        val application = requireNotNull(this.activity).application
        val viewModelFactory =
            ViewModelFactory(application)

        memoViewModel =
            ViewModelProvider(this, viewModelFactory).get(MemoViewModel::class.java)
        binding.rvMemoList.layoutManager = LinearLayoutManager(activity)

        binding.memoViewModel = memoViewModel
        binding.lifecycleOwner = this

        binding.rvMemoList.adapter =
            MemoAdapter(memoViewModel, MemoAdapter.OnClickListener { memoId ->
                //                navigateToMemoDetail(memoId)

            })


        binding.fabAddMemo.setOnClickListener {
            navigateToMemoEdited()

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * 생명주기를 고려한 메모리스트 로딩
         */
        getMemoList()
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    private fun getMemoList() {
        memoViewModel.allMemos.observe(viewLifecycleOwner, Observer {
            it?.let {
                (binding.rvMemoList.adapter as MemoAdapter).addSubmitList(it)
            }
        })
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
