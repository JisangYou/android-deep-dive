package com.example.memo.data

import androidx.lifecycle.LiveData


class MemoRepository(private val memoDao: MemoDao) {

    val allMemos: LiveData<List<Memo>> = memoDao.getMemos()

    suspend fun insert(memo: Memo) {
        memoDao.insertMemo(memo)
    }
}