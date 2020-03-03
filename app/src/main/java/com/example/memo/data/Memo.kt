package com.example.memo.data

import androidx.room.*
import com.example.memo.ImgConverters

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

//    @TypeConverters(ImgConverters::class)
//    val url: List<String>?

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val data: ByteArray? = null

)