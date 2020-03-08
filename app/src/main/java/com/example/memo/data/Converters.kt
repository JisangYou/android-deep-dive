package com.example.memo.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun listToString(value: List<String>?): String? {

        return Gson
    }

//    @TypeConverter
//    fun dateTofromUrlToString(date: Date?): Long? {
//        return date?.time?.toLong()
//    }
}