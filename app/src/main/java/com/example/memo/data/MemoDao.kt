package com.example.memo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.memo.data.Memo

@Dao
interface MemoDao {
    /**
     * Insert a task in the database. If the task already exists, replace it.
     *
     * @param memo
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemo(memo: Memo)

    @Update
    fun updateMemo(memo: Memo)

    @Query("SELECT * FROM memos")
    fun getMemos(): LiveData<List<Memo>>
//    fun getMemos(): List<Memo>

//    @Query("SELECT * FROM memos WHERE memoId=:memoId")
//    fun getMemoById(memoId: String)

//    @Query("DELETE FROM memos WHERE memoId = :memoId")
//    fun deleteTaskById(memoId: Long)
}