package com.example.memo.memoedited


import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
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
    private lateinit var editViewModel: MemoEditedViewModel

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

        editViewModel =
            ViewModelProvider(this, viewModelFactory).get(MemoEditedViewModel::class.java)


        /**
         * this의 의미,
         * 이게 왜 되는지에 대한 연구.
         */
        binding.editFragment = this
        /**
         * View model과 data 바인딩 세팅
         */
        binding.editViewModel = editViewModel
        binding.lifecycleOwner = this


        editViewModel.title.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "title  == $it")
        })

        editViewModel.description.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "description  == $it")
        })


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        Log.e(TAG, "onActivityResult")

        if (resultCode == Activity.RESULT_OK && requestCode == MemoEditedViewModel.IMAGE_PICK_CODE) {
//            image_view.setImageURI(data?.data)

            Log.e(TAG, "data == ${intent?.data}")
            val list = ArrayList<String>()
            if (intent!!.clipData == null) {
                list.add(intent.data.toString())
            } else {
                if (intent.clipData!!.itemCount > 10) {
                    Log.e(TAG, "아이템 개수가 10개 이상")
                } else {
                    for (i in 1..intent.clipData!!.itemCount) {
                        list.add(intent.clipData.toString())
                    }
                    Log.e(TAG, "list.size == " + list.size)
                }
            }


        }
    }
}
