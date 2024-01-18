package com.example.busroom.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.busroom.data.Schedule
import com.example.busroom.data.StationRepository


data class ScheduleUiState(
    var station : String="",
    var time : String =""
)
class ScheduleViewModel(private val stationRepository: StationRepository): ViewModel() {

    var itemUiState by mutableStateOf(ScheduleUiState())
    private set

    fun updateUiState(schedule: Schedule) {
        itemUiState = ScheduleUiState(station = schedule.StationName,)
    }
      suspend  fun saveItem() {
        if (validateInput()) {
            stationRepository.insertStation(itemUiState.toSchedule())
        }
    }
    private fun validateInput(): Boolean {
        return with(itemUiState) {
            itemUiState.time.isNotBlank() && itemUiState.station.isNotBlank()
        }
    }


}

fun ScheduleUiState.toSchedule():Schedule = Schedule(
   StationName= station,
    StationTime = time
)