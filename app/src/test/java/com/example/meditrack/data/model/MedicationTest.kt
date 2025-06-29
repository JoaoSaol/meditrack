package com.example.meditrack.data.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MedicationTest {

    @Test
    fun validMedication_shouldReturnTrue() {
        val medication = Medication(name = "Dipirona", dosage = "500mg", frequency = "8h", schedule = "8h, 16h, 24h")
        assertTrue(medication.isValid())
    }

    @Test
    fun invalidMedication_emptyName_shouldReturnFalse() {
        val medication = Medication(name = "", dosage = "500mg", frequency = "8h", schedule = "8h, 16h, 24h")
        assertFalse(medication.isValid())
    }

    @Test
    fun invalidMedication_emptyDosage_shouldReturnFalse() {
        val medication = Medication(name = "Dipirona", dosage = "", frequency = "8h", schedule = "8h, 16h, 24h")
        assertFalse(medication.isValid())
    }

    @Test
    fun invalidMedication_emptyFrequency_shouldReturnFalse() {
        val medication = Medication(name = "Dipirona", dosage = "500mg", frequency = "", schedule = "8h, 16h, 24h")
        assertFalse(medication.isValid())
    }

    @Test
    fun invalidMedication_emptySchedule_shouldReturnFalse() {
        val medication = Medication(name = "Dipirona", dosage = "500mg", frequency = "8h", schedule = "")
        assertFalse(medication.isValid())
    }
}

