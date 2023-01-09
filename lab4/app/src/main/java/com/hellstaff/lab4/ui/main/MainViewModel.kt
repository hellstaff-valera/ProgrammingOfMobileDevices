package com.hellstaff.lab4.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellstaff.lab4.data.TestRepo.testRepo
import com.hellstaff.lab4.models.MeteoritesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val repository: testRepo): ViewModel() {
    private val _all = MutableLiveData<MeteoritesResponse>()
    val all: LiveData<MeteoritesResponse>
        get() = _all

    init {
        getAll()
    }

    fun getAll() = viewModelScope.launch {
        repository.getAll().let {
            if(it.isSuccessful){
                _all.postValue(it.body())
            } else {
                Log.d("checkData", "Fail to load meteorites: ${it.errorBody()}")
            }
        }
    }
}