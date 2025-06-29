package com.example.meditrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meditrack.data.dao.MedicationDao
import com.example.meditrack.data.model.Medication

@Database(entities = [Medication::class], version = 1, exportSchema = false)
abstract class MedicationDatabase : RoomDatabase() {
    abstract fun medicationDao(): MedicationDao
}

