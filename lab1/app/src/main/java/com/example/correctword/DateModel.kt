package com.example.correctword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DateModel : ViewModel() {
    val timer: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}