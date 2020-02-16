package com.example.memo.memolist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memo.data.MemoDao

class MemoViewModelFactory(private val memoDao: MemoDao, private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            return MemoViewModel(memoDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
