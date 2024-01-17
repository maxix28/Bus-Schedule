package com.example.busroom

import android.app.Application

class ScheduleApplication:Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}