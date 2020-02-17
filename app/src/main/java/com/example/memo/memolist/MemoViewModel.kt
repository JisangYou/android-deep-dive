package com.example.memo.memolist

import android.app.Application
import androidx.lifecycle.*
import com.example.memo.data.Memo
import com.example.memo.data.MemoDao
import com.example.memo.data.MemoDatabase
import com.example.memo.data.MemoRepository
import kotlinx.coroutines.launch

class MemoViewModel(memoDao: MemoDao, application: Application) : AndroidViewModel(application) {

    private val memoRepository: MemoRepository

    val allMemos: LiveData<List<Memo>>

    init {
        val memosDao = MemoDatabase.getInstance(application).memoDao
        memoRepository = MemoRepository(memosDao)
        allMemos = memoRepository.allMemos
    }

    fun insertMemo(memo: Memo) = viewModelScope.launch {
        memoRepository.insert(memo)
    }



}