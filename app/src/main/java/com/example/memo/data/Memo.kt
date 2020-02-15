package com.example.memo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "memos")
data class Memo(

    @PrimaryKey @ColumnInfo(name = "memoId")
    var memoId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "startTimeMilli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "url")
    val url: String

)