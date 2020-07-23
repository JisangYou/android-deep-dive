package com.example.memo.data.model.db

import androidx.room.*

@Entity(tableName = "memos")
data class Memo(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "memoId")
    var memoId: Long,

    @ColumnInfo(name = "startTimeMilli")
    val startTimeMilli: Long,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "imgUrl")
    val imgUrl: List<String>?

)