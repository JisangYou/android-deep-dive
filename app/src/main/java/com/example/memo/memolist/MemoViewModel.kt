package com.example.memo.memolist

import android.app.Application
import androidx.lifecycle.*
import com.example.memo.data.Memo
import com.example.memo.data.MemoDao
import com.example.memo.data.MemoDatabase
import kotlinx.coroutines.launch

class MemoViewModel(memoDao: MemoDao, application: Application) : AndroidViewModel(application) {

    // apply 특정 객체를 생성함과 동시에 초기화

    private var _allMemos = MutableLiveData<List<Memo>>().apply { value = emptyList() }
    val allMemos: LiveData<List<Memo>>
        get() = _allMemos


    init {
        val memosDao = MemoDatabase.getInstance(application).memoDao
//        _allMemos = memosDao.getMemos() as MutableLiveData<List<Memo>>

    }


}