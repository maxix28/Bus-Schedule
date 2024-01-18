package com.example.busroom

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busroom.ui.screen.ScheduleViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ScheduleViewModel(scheduleApplication().container.stationRepository)
        }
    }

}
fun CreationExtras.scheduleApplication(): ScheduleApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as  ScheduleApplication)