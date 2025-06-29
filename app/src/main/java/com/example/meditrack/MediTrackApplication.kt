
package com.example.meditrack

import android.app.Application
import com.example.meditrack.di.AppContainer
import com.example.meditrack.di.AppDataContainer

class MediTrackApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

