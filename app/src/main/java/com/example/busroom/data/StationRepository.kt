package com.example.busroom.data

import kotlinx.coroutines.flow.Flow

interface StationRepository {
    suspend fun insertStation(station: Schedule)
    fun getAllStationStream(): Flow<List<Schedule>>
}