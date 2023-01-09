package com.hellstaff.lab4.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hellstaff.lab4.models.Meteorites

@Dao
interface MeteoritesDao {

    @Query("SELECT * FROM meteorites")
    fun getAllMeteorites(): List<Meteorites>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meteorites: Meteorites)

    @Delete
    suspend fun delete(meteorites: Meteorites)
}