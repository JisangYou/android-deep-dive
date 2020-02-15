package com.example.memo.memolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memo.data.Memo
import com.example.memo.data.MemoDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MemoViewModel(val database: MemoDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var memo = MutableLiveData<Memo?>()
    val memos = database.getMemos()

    init {

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}