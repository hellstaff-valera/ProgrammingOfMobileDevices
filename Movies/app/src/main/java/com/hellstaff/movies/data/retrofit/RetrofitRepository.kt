package com.hellstaff.movies.data.retrofit

import com.hellstaff.movies.data.retrofit.api.RetrofitInstance
import com.hellstaff.movies.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel>{
        return RetrofitInstance.api.getPopularMovie()
    }
}