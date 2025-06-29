package com.example.meditrack.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.meditrack.data.database.MedicationDatabase
import com.example.meditrack.data.model.Medication
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MedicationDaoTest {

    private lateinit var medicationDao: MedicationDao
    private lateinit var db: MedicationDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MedicationDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        medicationDao = db.medicationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertMedicationAndGetAll() = runBlocking {
        val medication = Medication(name = "Paracetamol", dosage = "500mg", frequency = "8h", schedule = "8h")
        medicationDao.insert(medication)
        val allMedications = medicationDao.getAll().first()
        assertEquals(1, allMedications.size)
        assertEquals("Paracetamol", allMedications[0].name)
    }

    @Test
    @Throws(Exception::class)
    fun updateMedication() = runBlocking {
        val medication = Medication(name = "Dipirona", dosage = "500mg", frequency = "6h", schedule = "6h")
        medicationDao.insert(medication)
        val retrievedMedication = medicationDao.getAll().first()[0]
        val updatedMedication = retrievedMedication.copy(dosage = "1000mg")
        medicationDao.update(updatedMedication)
        val allMedications = medicationDao.getAll().first()
        assertEquals("1000mg", allMedications[0].dosage)
    }

    @Test
    @Throws(Exception::class)
    fun getMedicationById() = runBlocking {
        val medication1 = Medication(name = "Aspirina", dosage = "100mg", frequency = "24h", schedule = "22h")
        val medication2 = Medication(name = "Dorflex", dosage = "300mg", frequency = "8h", schedule = "8h")
        medicationDao.insert(medication1)
        medicationDao.insert(medication2)

        val retrievedMedication = medicationDao.getById(medication1.id)
        assertEquals("Aspirina", retrievedMedication?.name)
    }
}

