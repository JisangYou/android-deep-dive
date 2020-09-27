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
import com.example.memo.EventObserver
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
    private lateinit var viewModel: MemoViewModel

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
        viewModel = ViewModelProvider(this, viewModelFactory).get(MemoViewModel::class.java)
        binding.rvMemoList.layoutManager = LinearLayoutManager(activity)
        binding.viewModel = viewModel
        binding.view = this
        binding.lifecycleOwner = this
        binding.rvMemoList.adapter = MemoAdapter(viewModel)
        viewModel.openTaskEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToMemoDetail(it)
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * 생명주기를 고려한 메모리스트 로딩
         */
        memoList()
    }


    private fun memoList() {
        viewModel.allMemos.observe(viewLifecycleOwner, Observer {
            it?.let {
                (binding.rvMemoList.adapter as MemoAdapter).addSubmitList(it)
            }
        })
    }

    /**
     * 뷰에 연결
     */
    fun navigateToMemoAdd() {
        val action = MemoListFragmentDirections
            .actionFragmentMemoListToMemoEdited()
        findNavController().navigate(action)
    }

    fun navigateToMemoDetail(memoId: Long) {
        val action = MemoListFragmentDirections.actionFragmentMemoListToMemoDetailFragment()
        Timber.d("move to DetailFragment$memoId")
        findNavController().navigate(action)
    }


}
