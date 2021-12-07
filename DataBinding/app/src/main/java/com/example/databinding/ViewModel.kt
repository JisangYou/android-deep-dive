package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    private val _lastName = MutableLiveData<String>()

    val lastName: LiveData<String>
        get() = _lastName

    private val _likes = MutableLiveData(0)

    val likes: LiveData<Int>
        get() = _likes

    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }
    fun onReset() {
        _likes.value = 0
    }

}