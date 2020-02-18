package com.example.memo.memoedited


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.memo.R
import com.example.memo.ViewModelFactory
import com.example.memo.data.MemoDatabase
import com.example.memo.databinding.FragmentMemoEditedBinding
import com.example.memo.databinding.FragmentMemoListBinding
import com.example.memo.memolist.MemoViewModel

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

        val editViewModel = ViewModelProvider(this, viewModelFactory).get(MemoViewModel::class.java)

        
        binding.fabAddMemo.setOnClickListener{
            binding.tvTitle.text = "molly"
            binding.tvDescription.text = "helllo"
        }



        return binding.root
    }


}
