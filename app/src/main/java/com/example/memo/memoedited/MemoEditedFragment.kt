package com.example.memo.memoedited


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.memo.R
import com.example.memo.ViewModelFactory
import com.example.memo.data.MemoDatabase
import com.example.memo.databinding.FragmentMemoEditedBinding


/**
 * A simple [Fragment] subclass.
 */
class MemoEditedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMemoEditedBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_memo_edited, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = MemoDatabase.getInstance(application).memoDao
        val viewModelFactory = ViewModelFactory(dataSource, application)

        val editViewModel =
            ViewModelProvider(this, viewModelFactory).get(MemoEditedViewModel::class.java)

        val TAG = javaClass.simpleName

        binding.fabAddMemo.setOnClickListener {
            editViewModel.addMemo()
//            Log.e(TAG, binding.tvDescription.text.toString())
//            Log.e(TAG, binding.tvTitle.text.toString())
        }

        val newTitle = binding.tvTitle

        val titleObserver = Observer<String> {titles-> newTitle.text  }

        return binding.root
    }


}
