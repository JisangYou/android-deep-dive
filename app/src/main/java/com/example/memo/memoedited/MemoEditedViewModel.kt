package com.example.memo.memoedited

import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.memo.data.Memo
import com.example.memo.data.MemoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MemoEditedViewModel(private val memoDao: MemoDao, application: Application) :
    AndroidViewModel(application) {

    // xml 상에서 세팅된 변수 값

    val TAG = javaClass.simpleName
    // Two-way databinding, exposing MutableLiveData
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val imgUriList = MutableLiveData<List<String>>();


    private suspend fun insert(newTitle: String?, newDescription: String?) {
        withContext(Dispatchers.IO) {
            memoDao.insertMemo(
                Memo(
                    0,
                    System.currentTimeMillis(),
                    newTitle,
                    newDescription
                )
            )
        }
    }


    fun addMemo() {
        viewModelScope.launch {
            insert(title.value, description.value)
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
        var intent: Intent? = null
        viewModelScope.launch {
            intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->

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

    fun getPicture(str: List<String>) {
        viewModelScope.launch {

        }
    }

    companion object {
        //image pick code
        const val IMAGE_PICK_CODE = 1000;
        const val TAKE_PICK_CODE = 1001;
    }

}