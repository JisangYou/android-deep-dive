package com.example.memo.memolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.memo.data.MemoDatabaseDao

class MemoViewModel(val database: MemoDatabaseDao, application: Application) : AndroidViewModel(application) {

}