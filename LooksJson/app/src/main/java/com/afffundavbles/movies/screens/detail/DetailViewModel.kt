package com.afffundavbles.movies.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afffundavbles.movies.REALISATION
import com.afffundavbles.movies.models.MovieItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel:  ViewModel() {

    fun insert(movieItemModel: MovieItemModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REALISATION.insertMovie(movieItemModel){
                onSuccess()
            }
        }
    fun delete(movieItemModel: MovieItemModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REALISATION.deleteMovie(movieItemModel){
                onSuccess()
            }
        }



}