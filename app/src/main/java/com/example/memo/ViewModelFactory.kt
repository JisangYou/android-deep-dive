package com.example.memo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memo.ui.detail.MemoDetailViewModel
import com.example.memo.ui.edited.MemoEditedViewModel
import com.example.memo.ui.list.MemoViewModel

class ViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            return MemoViewModel(
                application
            ) as T
        } else if (modelClass.isAssignableFrom(MemoDetailViewModel::class.java)) {
            return MemoDetailViewModel(
                application
            ) as T
        } else if (modelClass.isAssignableFrom(MemoEditedViewModel::class.java)) {
            return MemoEditedViewModel(
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
