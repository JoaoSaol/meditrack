package com.example.meditrack.data.repository

import com.example.meditrack.data.dao.MedicationDao
import com.example.meditrack.data.model.Medication
import kotlinx.coroutines.flow.Flow

interface MedicationRepository {
    suspend fun insertMedication(medication: Medication)
    suspend fun updateMedication(medication: Medication)
    fun getAllMedications(): Flow<List<Medication>>
    suspend fun getMedicationById(id: Int): Medication?
}

class MedicationRepositoryImpl(private val medicationDao: MedicationDao) : MedicationRepository {
    override suspend fun insertMedication(medication: Medication) = medicationDao.insert(medication)
    override suspend fun updateMedication(medication: Medication) = medicationDao.update(medication)
    override fun getAllMedications(): Flow<List<Medication>> = medicationDao.getAll()
    override suspend fun getMedicationById(id: Int): Medication? = medicationDao.getById(id)
}

