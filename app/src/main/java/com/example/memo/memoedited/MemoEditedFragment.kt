package com.example.memo.memoedited

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.R
import com.example.memo.ViewModelFactory
import com.example.memo.data.MemoDatabase
import com.example.memo.databinding.FragmentMemoEditedBinding
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MemoEditedFragment : Fragment() {


    val TAG = javaClass.simpleName
    private lateinit var editViewModel: MemoEditedViewModel
    private lateinit var binding: FragmentMemoEditedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_memo_edited, container, false
        )
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ViewModelFactory(application)

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
        binding.rvImageList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        Log.e(TAG, "layoutManager")
        binding.rvImageList.adapter =
            MemoEditedAdapter(editViewModel, MemoEditedAdapter.OnClickListener {
                /**
                 * 바인딩 연결
                 */
            })
        binding.fabMenu.setOnClickListener {
            if (!editViewModel.fbFlag.value!!) {
                editViewModel.fbFlag.value = true
                binding.fabAddImage.show()
                binding.fabAddMemo.show()
                binding.fabPictureImage.show()
            } else {
                editViewModel.fbFlag.value = false
                binding.fabAddImage.hide()
                binding.fabAddMemo.hide()
                binding.fabPictureImage.hide()
            }
        }


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        Log.e(TAG, "onActivityResult")

        if (resultCode == Activity.RESULT_OK && requestCode == MemoEditedViewModel.IMAGE_PICK_CODE) {

            val list = ArrayList<String>()
            if (intent!!.clipData == null) {
                /**
                 * null이니 없어도 됨.
                 */
//                list.add(intent.data.toString())
            } else {
                if (intent.clipData!!.itemCount > 10) {
                    Log.e(TAG, "아이템 개수가 10개 이상")
                } else {
                    for (i in 0 until intent.clipData!!.itemCount) {
                        Log.e(
                            TAG,
                            "intent.clipData!!.getItemAt(i) == " + intent.clipData!!.getItemAt(i).uri.toString()
                        )
                        list.add(intent.clipData!!.getItemAt(i).uri.toString())
                    }
                    editViewModel.urlList.value = list
                    (binding.rvImageList.adapter as MemoEditedAdapter).addSubmitList(list)
                }
            }
        }
    }
}
