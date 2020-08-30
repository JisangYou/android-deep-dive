package com.example.memo.ui.list


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.R
import com.example.memo.ViewModelFactory

import com.example.memo.databinding.FragmentMemoListBinding
import timber.log.Timber

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
        val viewModelFactory = ViewModelFactory(application)
        memoViewModel = ViewModelProvider(this, viewModelFactory).get(MemoViewModel::class.java)
        binding.rvMemoList.layoutManager = LinearLayoutManager(activity)
        binding.viewModel = memoViewModel
        binding.view = this
        binding.lifecycleOwner = this
        binding.rvMemoList.adapter = MemoAdapter(memoViewModel)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * 생명주기를 고려한 메모리스트 로딩
         */
        getMemoList()
    }


    private fun getMemoList() {
        memoViewModel.allMemos.observe(viewLifecycleOwner, Observer {
            it?.let {
                (binding.rvMemoList.adapter as MemoAdapter).addSubmitList(it)
            }
        })
    }

    /**
     * 뷰에 연결
     */
    fun navigateToMemoEdited() {
        val action = MemoListFragmentDirections
            .actionFragmentMemoListToMemoEdited()
        findNavController().navigate(action)
    }

    private fun navigateToMemoDetail(memoId: Long) {
        val action = MemoListFragmentDirections.actionFragmentMemoListToMemoDetailFragment(memoId)
        Timber.e("move to DetailFragment")
        findNavController().navigate(action)
    }

}
