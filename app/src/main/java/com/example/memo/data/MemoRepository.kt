package com.example.memo.data

import android.app.Application
import androidx.lifecycle.LiveData


class MemoRepository(memo: Memo, application: Application) {

    private val memoDatabase = MemoDatabase.getInstance(application)
    private val memoDao: MemoDao = memoDatabase.memoDao
    private val memos: LiveData<List<Memo>> = memoDao.getMemos()
    private val addMemo = memoDao.insertMemo(memo)
    private val updateMemo = memoDao.updateMemo(memo)


    fun getMemos(): LiveData<List<Memo>> = memos
    fun insertMemo() = addMemo
    fun updateMemo() = updateMemo


}