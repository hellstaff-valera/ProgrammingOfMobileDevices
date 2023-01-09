package com.hellstaff.lab4.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "meteorites")
data class Meteorites(
    val fall: String,
    val geolocation: String,
    val id: Long,
    val mass: String,
    val name: String,
    val nametype: String,
    val recclass: String,
    val reclat: String,
    val reclong: String,
    val year: String
) : Serializable