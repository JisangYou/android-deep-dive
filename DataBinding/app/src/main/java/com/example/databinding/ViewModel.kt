package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    private val _lastName = MutableLiveData<String>()

    val lastName: LiveData<String>
        get() = _lastName

}