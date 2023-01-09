package com.afffundavbles.movies.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afffundavbles.movies.REALISATION
import com.afffundavbles.movies.models.MovieItemModel

class FavoriteFragmentViewModel: ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>>{
        return REALISATION.allMovies
    }
}