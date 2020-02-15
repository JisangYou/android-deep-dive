package com.example.memo.data

import androidx.room.*

@Dao
interface MemoDatabaseDao {
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
    fun getMemos()

    @Query("SELECT * FROM memos WHERE memoId=:memoId")
    fun getMemoById(memoId: String)

    @Query("DELETE FROM memos WHERE memoId = :memoId")
    fun deleteTaskById(memoId: String): Int
}