package com.afffundavbles.movies.data.retrofit

import com.afffundavbles.movies.data.retrofit.api.RetrofitInstance
import com.afffundavbles.movies.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel>{
        return RetrofitInstance.api.getPopularMovie()
    }
}