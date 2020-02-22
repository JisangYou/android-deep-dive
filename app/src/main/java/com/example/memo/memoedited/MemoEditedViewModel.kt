package com.example.memo.memoedited

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
}