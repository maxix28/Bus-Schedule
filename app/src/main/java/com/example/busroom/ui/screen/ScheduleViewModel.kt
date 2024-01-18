package com.example.busroom.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busroom.data.Schedule
import com.example.busroom.data.StationRepository
import kotlinx.coroutines.launch


data class ScheduleUiState(
    var station : String="",
    var time : String =""
)
class ScheduleViewModel(private val stationRepository: StationRepository): ViewModel() {

    var itemUiState : ScheduleUiState by mutableStateOf(ScheduleUiState())
    private set




    fun updateUiState(schedule: Schedule) {
        itemUiState = ScheduleUiState(station = schedule.StationName,)
    }
    fun setStation(it: String){
      val curretnUi =  itemUiState
        itemUiState = curretnUi.copy(station = it)
    }
    fun setTime(it: String){
      val curretnUi =  itemUiState
        itemUiState = curretnUi.copy(time = it)
    }
      suspend  fun saveItem() {

              if (validateInput()) {

                  println("STTTTART")
                  stationRepository.insertStation(itemUiState.toSchedule())
                  itemUiState.station=""
                  itemUiState.time=""

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