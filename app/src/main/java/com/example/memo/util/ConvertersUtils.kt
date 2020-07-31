package com.example.memo.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ConvertersUtils {
    private var gson = Gson()
    @TypeConverter
    fun toListOfStrings(urlStrList: List<String>?): String? {

        return gson.toJson(urlStrList)
    }

    @TypeConverter
    fun fromListOfStrings(url: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type

        return gson.fromJson(url, listType)
    }

//    @TypeConverter
//    fun toListOfStrings(flatStringList: String): List<String> {
//        return flatStringList.split(",")
//    }
//    @TypeConverter
//    fun fromListOfStrings(listOfString: List<String>): String {
//        return listOfString.joinToString(",")
//    }
}