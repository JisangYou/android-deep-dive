package com.example.memo.ui.edited

import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.memo.data.local.MemoDatabase
import com.example.memo.data.local.MemoRepository
import kotlinx.coroutines.launch


class MemoEditedViewModel(application: Application) :
    AndroidViewModel(application) {

    // xml 상에서 세팅된 변수 값

    val TAG = javaClass.simpleName
    // Two-way databinding, exposing MutableLiveData
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    var urlList = MutableLiveData<List<String>>()
    private val memoRepository: MemoRepository

    init {

        val memoDao = MemoDatabase.getInstance(application).memoDao()
        memoRepository = MemoRepository(memoDao)
    }

    fun addMemo() {
        viewModelScope.launch {
            memoRepository.insert(title.value, description.value, urlList.value)
        }
    }

    fun addImage(fragment: MemoEditedFragment) {
        var intent: Intent? = null
        viewModelScope.launch {
            intent = Intent(Intent.ACTION_PICK)
            intent!!.type = "image/*"
            intent!!.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent!!.type = MediaStore.Images.Media.CONTENT_TYPE
            fragment.startActivityForResult(Intent.createChooser(intent, " 다중 선택"), IMAGE_PICK_CODE)
        }
    }

    fun takePicture(fragment: MemoEditedFragment) {
        viewModelScope.launch {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->

                /**
                 * 권한 설정을 거절 헀을 수도 있으니까...
                 */
                try {
                    fragment.startActivityForResult(takePictureIntent, TAKE_PICK_CODE)
                } catch (e: SecurityException) {
                    Log.e(TAG, e.toString())
                }
            }
        }
    }

    companion object {
        //image pick code
        const val IMAGE_PICK_CODE = 1000;
        const val TAKE_PICK_CODE = 1001;
    }

}