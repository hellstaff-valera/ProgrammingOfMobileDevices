package com.hellstaff.lab4.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hellstaff.lab4.models.Meteorites

@Database(entities = [Meteorites::class], version = 1, exportSchema = true)
abstract class MeteoritesDatabase: RoomDatabase() {
    abstract fun getMeteoritesDao(): MeteoritesDao

    companion object{
        private var instance: MeteoritesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context): MeteoritesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MeteoritesDatabase::class.java,
                "meteorites_database"
            ).build()
        }
    }
}