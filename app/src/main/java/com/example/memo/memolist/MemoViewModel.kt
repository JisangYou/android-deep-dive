package com.example.memo.memolist

import android.app.Application
import androidx.lifecycle.*
import com.example.memo.data.Memo
import com.example.memo.data.MemoDao
import com.example.memo.data.MemoDatabase
import com.example.memo.data.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(memoRepository: MemoRepository, application: Application) : AndroidViewModel(application) {

    // apply 특정 객체를 생성함과 동시에 초기화

    private var _allMemos = MutableLiveData<List<Memo>>().apply { value = emptyList() }
    val allMemos: LiveData<List<Memo>>
        get() = _allMemos


    private val memoRepository = memoRepository

    init {


    }


    private suspend fun initializeMemo() {
        withContext(Dispatchers.IO) {
            memoRepository.getMemos()
        }
    }
}