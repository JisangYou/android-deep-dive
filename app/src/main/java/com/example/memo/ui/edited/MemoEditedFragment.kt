package com.example.memo.ui.edited

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
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
import com.example.memo.databinding.FragmentMemoEditedBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel


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

    //TODO test =========================================

    @Throws(IOException::class)
    private fun copyFile(sourceFile: File, destFile: File) {
        if (!sourceFile.exists()) {
            return
        }
        var source: FileChannel? = null
        var destination: FileChannel? = null
        source = FileInputStream(sourceFile).channel
        destination = FileOutputStream(destFile).channel
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size())
        }
        if (source != null) {
            source.close()
        }
        if (destination != null) {
            destination.close()
        }
    }

    private fun getPath(contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Video.Media.DATA)
        val cursor: Cursor = this.activity!!.managedQuery(contentUri, proj, null, null, null)
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    //TODO test =========================================

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

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

                        list.add(intent.clipData!!.getItemAt(i).uri.toString())
                        val destFile =
                            File(context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + ".jpg")
                        Log.e(TAG, "check == $destFile")
                        copyFile(File(getPath(intent.getData()!!)), destFile)

                    }
                    editViewModel.urlList.value = list
                    (binding.rvImageList.adapter as MemoEditedAdapter).addSubmitList(list)
                }
            }
        }
    }
}
