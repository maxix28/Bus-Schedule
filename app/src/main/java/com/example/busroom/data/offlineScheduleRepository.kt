package com.example.busroom.data

import kotlinx.coroutines.flow.Flow

class offlineScheduleRepository(private val stationDao: StationDao):StationRepository {
    override suspend fun insertStation(station: Schedule) =stationDao.addSttion(station)
    override fun getAllStationStream(): Flow<List<Schedule>> =stationDao.getAllItems()
}