package com.example.memo.ui.list

import android.app.Application
import androidx.lifecycle.*
import com.example.memo.data.model.db.Memo
import com.example.memo.data.local.MemoDatabase
import com.example.memo.data.local.MemoRepository

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