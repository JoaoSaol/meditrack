package com.example.meditrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dosage: String,
    val frequency: String,
    val schedule: String,
    var status: String = "Pendente"
) {
    fun isValid(): Boolean {
        return name.isNotBlank() && dosage.isNotBlank() && frequency.isNotBlank() && schedule.isNotBlank()
    }
}

