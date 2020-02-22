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

    val TAG = javaClass.simpleName
    // apply 특정 객체를 생성함과 동시에 초기화
    private var _allMemos = MutableLiveData<List<Memo>>().apply { value = emptyList() }
    val allMemos: LiveData<List<Memo>>
        get() = _allMemos

    init {
        viewModelScope.launch {
            _allMemos.value = initializeMemo()
        }
    }

    private suspend fun initializeMemo(): ArrayList<Memo> {
        val memoToShow = ArrayList<Memo>()
        withContext(Dispatchers.IO) {
            if (memoDao.getMemos().isEmpty()) {
                Log.e(TAG, "null")
            } else {
                val memoList = memoDao.getMemos()
                Log.e(TAG, "memoList = " + memoList.size)
                for (memo in memoList) {
                    Log.e(TAG, memo.title + " : " + memoList.size)
                    memoToShow.add(memo)
                }
            }
        }
        return memoToShow
    }


}