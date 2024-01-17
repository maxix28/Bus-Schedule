package com.example.busroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val StationName : String,
    val StationTime : String,

)
