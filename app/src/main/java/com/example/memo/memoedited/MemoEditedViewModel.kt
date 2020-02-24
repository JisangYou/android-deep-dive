package com.example.memo.memoedited

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.memo.MainActivity
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
            intent = Intent(Intent.ACTION_GET_CONTENT)
            intent!!.type = "image/*"
            fragment.startActivityForResult(intent, IMAGE_PICK_CODE)
        }
    }

    companion object {
        //image pick code
        const val IMAGE_PICK_CODE = 1000;
    }

}