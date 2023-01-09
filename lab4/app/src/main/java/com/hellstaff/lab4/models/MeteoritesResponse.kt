package com.hellstaff.lab4.models

data class MeteoritesResponse(
    val meteorites: List<Meteorites>,
    val status: String,
    val totalResults: Int
)