package com.jetpackcomposelearning.statetest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _count = MutableLiveData<Int>(0)

    private val _doubleCount = MutableLiveData<Int>(0)


    val count: MutableLiveData<Int> = _count

    val doubleCount: MutableLiveData<Int> = _doubleCount

    fun incrementCount() {
        _count.value = (_count.value ?: 0).plus(1)
    }

    fun incrementDoubleCount() {
        _doubleCount.value = (_doubleCount.value ?: 0).plus(2)
    }
}