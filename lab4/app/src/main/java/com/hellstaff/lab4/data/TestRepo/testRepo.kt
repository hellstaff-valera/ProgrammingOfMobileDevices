package com.hellstaff.lab4.data.TestRepo

import com.hellstaff.lab4.data.api.MeteoritesService
import javax.inject.Inject

class testRepo @Inject constructor(private val meteoritesService: MeteoritesService) {
    suspend fun getAll() = meteoritesService.getMeteorites()
}