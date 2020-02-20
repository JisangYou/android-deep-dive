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
    val title = MutableLiveData<String>()
//    val title: LiveData<String> get() = _title

    val description = MutableLiveData<String>()
//    val description: LiveData<String> get() = _description

    /**
     * 아래는 xml 에러가...
     */
//    private var _title = MutableLiveData<String>()
//    val title: LiveData<String> get() = _title
//
//    private var _description = MutableLiveData<String>()
//    val description: LiveData<String> get() = _description

    init {

    }


    private suspend fun insert() {
        withContext(Dispatchers.IO) {
            //            memoDao.insertMemo(
//                Memo(
//
//                )
//
//            )
            Log.e(TAG, "checking ${title.value} ${description.value}")
        }
    }


    fun addMemo() {
        viewModelScope.launch {
            insert()

        }
    }
}