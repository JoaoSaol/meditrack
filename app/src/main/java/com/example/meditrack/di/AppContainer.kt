package com.example.meditrack.di

import android.content.Context
import androidx.room.Room
import com.example.meditrack.data.database.MedicationDatabase
import com.example.meditrack.data.repository.MedicationRepository
import com.example.meditrack.data.repository.MedicationRepositoryImpl

interface AppContainer {
    val medicationDatabase: MedicationDatabase
    val medicationRepository: MedicationRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val medicationDatabase: MedicationDatabase by lazy {
        Room.databaseBuilder(
            context,
            MedicationDatabase::class.java,
            "medication_database"
        ).build()
    }

    override val medicationRepository: MedicationRepository by lazy {
        MedicationRepositoryImpl(medicationDatabase.medicationDao())
    }
}

