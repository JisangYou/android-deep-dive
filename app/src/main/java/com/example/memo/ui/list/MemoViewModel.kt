package com.example.memo.ui.list

import android.app.Application
import androidx.lifecycle.*
import com.example.memo.Event
import com.example.memo.data.model.db.Memo
import com.example.memo.data.local.MemoDatabase
import com.example.memo.data.local.MemoRepository
import timber.log.Timber

class MemoViewModel(application: Application) :
    AndroidViewModel(application) {

    val TAG = javaClass.simpleName
    private val memoRepository: MemoRepository
    val allMemos: LiveData<List<Memo>>
    private val _openTaskEvent = MutableLiveData<Event<Long>>()
    val openTaskEvent: LiveData<Event<Long>> = _openTaskEvent

    init {

        val memoDao = MemoDatabase.getInstance(application).memoDao()
        memoRepository = MemoRepository(memoDao)

        allMemos = memoRepository.getAllMemos()
    }

    /**
     * Called by Data Binding.
     */
    fun openTask(memoId: Long) {
        _openTaskEvent.value = Event(memoId)
    }
}