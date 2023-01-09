package com.hellstaff.movies.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hellstaff.movies.REALISATION
import com.hellstaff.movies.models.MovieItemModel

class FavoriteFragmentViewModel: ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>>{
        return REALISATION.allMovies
    }
}