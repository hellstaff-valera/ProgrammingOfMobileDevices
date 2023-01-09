package com.hellstaff.lab4.data.api

import com.hellstaff.lab4.models.MeteoritesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MeteoritesService {
    @GET("resource/gh4g-9sfh.json")
    suspend fun getMeteorites(): Response<MeteoritesResponse>
}