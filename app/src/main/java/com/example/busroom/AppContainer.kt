package com.example.busroom

import android.content.Context
import com.example.busroom.data.StationDataBase
import com.example.busroom.data.StationRepository
import com.example.busroom.data.offlineScheduleRepository

interface AppContainer {
    val stationRepository: StationRepository
}

class DefaultAppContainer (val context: Context):AppContainer{
    override val stationRepository: StationRepository by lazy {
        offlineScheduleRepository(StationDataBase.getDatabase(context).stationDao())
    }
}