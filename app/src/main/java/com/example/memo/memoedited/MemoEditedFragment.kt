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


    val TAG = javaClass.simpleName


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


        binding.fabAddMemo.setOnClickListener {
            editViewModel.addMemo()

            editViewModel.title.value = binding.tvTitle.text.toString()
            editViewModel.description.value = binding.tvDescription.text.toString()

            Log.e(TAG, editViewModel.title.toString() + " ==== ")
            Log.e(TAG, editViewModel.description.toString() + " ==== ")
        }



        editViewModel.title.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "it    $it ==== ")

        })

        return binding.root
    }


}
