package com.hellstaff.movies.data.retrofit.api

import com.hellstaff.movies.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=0cf0f9e6f7bd35031c8f15fcccd545b8&language=uk-UA&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>
}