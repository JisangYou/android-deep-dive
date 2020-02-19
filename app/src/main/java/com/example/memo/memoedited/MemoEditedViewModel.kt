package com.example.memo.memoedited

import android.app.Application
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

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    private var memoId: Long = 0

    private var isNewTask: Boolean = false


    private suspend fun insertMemo() {
        withContext(Dispatchers.IO) {
            memoDao.insertMemo(Memo(0, 1111,"haha", "hehe"))
            Log.e("insertMemo","checking")
        }
    }

    fun addMemo() {
        viewModelScope.launch {
            insertMemo()
        }


    }
}