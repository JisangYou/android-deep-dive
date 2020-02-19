package com.example.memo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memo.data.MemoDao
import com.example.memo.memodetail.MemoDetailViewModel
import com.example.memo.memoedited.MemoEditedViewModel
import com.example.memo.memolist.MemoViewModel

class ViewModelFactory(private val memoDao: MemoDao, private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            return MemoViewModel(
                memoDao,
                application
            ) as T
        } else if (modelClass.isAssignableFrom(MemoDetailViewModel::class.java)) {
            return MemoDetailViewModel(
                memoDao,
                application
            ) as T
        } else if (modelClass.isAssignableFrom(MemoEditedViewModel::class.java)) {
            return MemoEditedViewModel(
                memoDao,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
