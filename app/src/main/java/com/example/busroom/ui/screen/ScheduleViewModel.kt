package com.example.busroom.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busroom.data.Schedule
import com.example.busroom.data.StationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


data class ScheduleUiState(
    var station : String="",
    var time : String =""
)
data class  ListScheduleUI(
    var list :List<Schedule> = listOf()
)
class ScheduleViewModel(private val stationRepository: StationRepository): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    var itemUiState : ScheduleUiState by mutableStateOf(ScheduleUiState())
    private set

var ListUIState: StateFlow<ListScheduleUI> = stationRepository.getAllStationStream().map{
    ListScheduleUI(it)
} .stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
    initialValue = ListScheduleUI()
)


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
                  val curretnUi =  itemUiState
                  itemUiState = curretnUi.copy(time = "", station = "")

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