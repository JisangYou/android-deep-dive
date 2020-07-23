package com.example.memo.data.local

import androidx.lifecycle.LiveData
import com.example.memo.data.local.db.dao.MemoDao
import com.example.memo.data.model.db.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoRepository(private val memoDao: MemoDao) {


    fun getAllMemos(): LiveData<List<Memo>> {
        return memoDao.getMemos()
    }

    suspend fun insert(title: String?, description: String?, urlList: List<String>?) {
        withContext(Dispatchers.IO) {
            val memo = Memo(
                0,
                System.currentTimeMillis(),
                title,
                description,
                urlList
            )
            memoDao.insertMemo(memo)
        }
    }
}