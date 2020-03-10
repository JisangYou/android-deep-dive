package com.example.memo.memolist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.memo.data.Memo
import com.example.memo.data.MemoDao
import com.example.memo.data.MemoDatabase
import com.example.memo.data.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(application: Application) :
    AndroidViewModel(application) {

    val TAG = javaClass.simpleName
    private val memoRepository: MemoRepository
    val allMemos: LiveData<List<Memo>>
    init {

        val memoDao = MemoDatabase.getInstance(application).memoDao()
        memoRepository = MemoRepository(memoDao)

        allMemos = memoRepository.getAllMemos()
    }
}