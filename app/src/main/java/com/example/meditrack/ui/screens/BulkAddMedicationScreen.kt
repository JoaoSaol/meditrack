package com.example.meditrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditrack.data.model.Medication
import com.example.meditrack.ui.viewmodel.MedicationViewModel
import com.example.meditrack.MediTrackApplication
import com.example.meditrack.util.NotificationScheduler

data class MedicationForm(
    var name: String = "",
    var dosage: String = "",
    var frequency: String = "",
    var schedule: String = ""
) {
    fun isValid(): Boolean {
        return name.isNotBlank() && dosage.isNotBlank() && frequency.isNotBlank() && schedule.isNotBlank()
    }
    
    fun toMedication(): Medication {
        return Medication(
            name = name,
            dosage = dosage,
            frequency = frequency,
            schedule = schedule
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BulkAddMedicationScreen(onSaveClick: () -> Unit) {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as MediTrackApplication).container
    val medicationViewModel: MedicationViewModel = viewModel(factory = MedicationViewModel.MedicationViewModelFactory(appContainer.medicationRepository))
    val notificationScheduler = remember { NotificationScheduler(context) }
    
    var medications by remember { mutableStateOf(listOf(MedicationForm())) }
    var isSaving by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Adicionar Múltiplos Medicamentos") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    medications = medications + MedicationForm()
                }
            ) {
                Icon(Icons.Filled.Add, "Adicionar medicamento")
            }
        }
    ) { _ ->
        BulkAddMedicationContent(
            medications = medications,
            isSaving = isSaving,
            onMedicationsChange = { medications = it },
            onCancel = onSaveClick,
            onSaveAll = { validMedications ->
                isSaving = true
                validMedications.forEach { medicationForm ->
                    val medication = medicationForm.toMedication()
                    medicationViewModel.insertMedication(medication)
                    notificationScheduler.scheduleNotification(medication)
                }
                onSaveClick()
            }
        )
    }
}

@Composable
private fun BulkAddMedicationContent(
    medications: List<MedicationForm>,
    isSaving: Boolean,
    onMedicationsChange: (List<MedicationForm>) -> Unit,
    onCancel: () -> Unit,
    onSaveAll: (List<MedicationForm>) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(medications) { index, medication ->
                MedicationFormCard(
                    medication = medication,
                    onMedicationChange = { updatedMedication ->
                        onMedicationsChange(medications.toMutableList().apply {
                            this[index] = updatedMedication
                        })
                    },
                    onDelete = if (medications.size > 1) {
                        {
                            onMedicationsChange(medications.toMutableList().apply {
                                removeAt(index)
                            })
                        }
                    } else null,
                    medicationNumber = index + 1
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            val validMedications = medications.filter { it.isValid() }
            Button(
                onClick = { onSaveAll(validMedications) },
                enabled = !isSaving && validMedications.isNotEmpty(),
                modifier = Modifier.weight(1f)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Salvar Todos")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationFormCard(
    medication: MedicationForm,
    onMedicationChange: (MedicationForm) -> Unit,
    onDelete: (() -> Unit)?,
    medicationNumber: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            MedicationFormHeader(medicationNumber, onDelete)
            Spacer(modifier = Modifier.height(8.dp))
            MedicationFormFields(medication, onMedicationChange)
            Spacer(modifier = Modifier.height(8.dp))
            MedicationValidationIndicator(medication)
        }
    }
}

@Composable
private fun MedicationFormHeader(
    medicationNumber: Int,
    onDelete: (() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Medicamento $medicationNumber",
            style = MaterialTheme.typography.titleMedium
        )
        onDelete?.let {
            IconButton(onClick = it) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Remover medicamento",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun MedicationFormFields(
    medication: MedicationForm,
    onMedicationChange: (MedicationForm) -> Unit
) {
    OutlinedTextField(
        value = medication.name,
        onValueChange = { onMedicationChange(medication.copy(name = it)) },
        label = { Text("Nome do Medicamento") },
        modifier = Modifier.fillMaxWidth(),
        isError = false
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = medication.dosage,
            onValueChange = { onMedicationChange(medication.copy(dosage = it)) },
            label = { Text("Dosagem") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = medication.frequency,
            onValueChange = { onMedicationChange(medication.copy(frequency = it)) },
            label = { Text("Frequência") },
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = medication.schedule,
        onValueChange = { onMedicationChange(medication.copy(schedule = it)) },
        label = { Text("Horário (ex: 8h, 14h, 20h)") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MedicationValidationIndicator(medication: MedicationForm) {
    if (medication.name.isNotBlank() || medication.dosage.isNotBlank() ||
        medication.frequency.isNotBlank() || medication.schedule.isNotBlank()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        val isValid = medication.isValid()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isValid) Icons.Filled.Add else Icons.Filled.Delete,
                contentDescription = if (isValid) "Válido" else "Inválido",
                tint = if (isValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (isValid) "Medicamento válido" else "Preencha todos os campos",
                style = MaterialTheme.typography.bodySmall,
                color = if (isValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
}
