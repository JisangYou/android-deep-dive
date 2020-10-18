package com.example.memo.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.memo.data.local.MemoDatabase
import com.example.memo.data.local.MemoRepository
import com.example.memo.data.model.db.Memo
import kotlinx.coroutines.launch
import timber.log.Timber
import java.sql.Time

class MemoDetailViewModel(application: Application) :
    AndroidViewModel(application) {

    val TAG = javaClass.simpleName

    // Two-way databinding, exposing MutableLiveData
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    var urlList = MutableLiveData<List<String>>()
    private val memoRepository: MemoRepository

    init {
        val memoDao = MemoDatabase.getInstance(application).memoDao()
        memoRepository = MemoRepository(memoDao)
    }

    fun getMemoById(memoId: Long) {

       val memo =  memoRepository.getMemoById(memoId)
        Timber.d(memo.value?.title.toString())
        Timber.d(memo.value?.description.toString())
    }
}