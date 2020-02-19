package com.example.memo.memolist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.memo.data.Memo
import com.example.memo.data.MemoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(private val memoDao: MemoDao, application: Application) :
    AndroidViewModel(application) {

    // apply 특정 객체를 생성함과 동시에 초기화
    private var _allMemos = MutableLiveData<List<Memo>>().apply { value = emptyList() }
    val allMemos: LiveData<List<Memo>>
        get() = _allMemos

    init {
        viewModelScope.launch {
            initializeMemo()
        }
    }

    private suspend fun initializeMemo() {
        withContext(Dispatchers.IO) {
            //            memoDao.getMemos()
//            Log.e("initializeMemo","memoDao.getMemos() === "+ memoDao.getMemos())

//            for (i in 0..memoDao.getMemos().size) {
//                Log.e("initializeMemo","memoDao.getMemos() === "+ memoDao.getMemos().get(i))
//            }
        }
    }


}