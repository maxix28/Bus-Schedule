package com.example.busroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
abstract class StationDataBase: RoomDatabase() {
    abstract fun stationDao ():StationDao

    companion object {
        @Volatile
        private var Instance: StationDataBase? = null

        fun getDatabase(context: Context): StationDataBase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StationDataBase::class.java, "schedule_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}