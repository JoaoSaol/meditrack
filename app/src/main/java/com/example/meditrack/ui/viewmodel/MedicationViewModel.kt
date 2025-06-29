package com.example.meditrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meditrack.data.model.Medication
import com.example.meditrack.data.repository.MedicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MedicationViewModel(private val medicationRepository: MedicationRepository) : ViewModel() {

    val allMedications: Flow<List<Medication>> = medicationRepository.getAllMedications()

    fun insertMedication(medication: Medication) {
        viewModelScope.launch {
            medicationRepository.insertMedication(medication)
        }
    }

    fun updateMedication(medication: Medication) {
        viewModelScope.launch {
            medicationRepository.updateMedication(medication)
        }
    }

    fun markMedicationAsTaken(medication: Medication) {
        viewModelScope.launch {
            val updatedMedication = medication.copy(status = "Tomado")
            medicationRepository.updateMedication(updatedMedication)
        }
    }

    fun getMedicationById(id: Int): Flow<Medication?> = flow {
        emit(medicationRepository.getMedicationById(id))
    }

    class MedicationViewModelFactory(private val medicationRepository: MedicationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MedicationViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MedicationViewModel(medicationRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

