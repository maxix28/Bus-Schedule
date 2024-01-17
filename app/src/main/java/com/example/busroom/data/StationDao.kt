package com.example.busroom.data

import android.media.RouteListingPreference
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSttion(station : Schedule)
    @Query("SELECT * from Schedule ORDER BY StationTime ASC")
    fun getAllItems(): Flow<List<RouteListingPreference.Item>>
}