package com.example.meditrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditrack.data.model.Medication
import com.example.meditrack.ui.viewmodel.MedicationViewModel
import com.example.meditrack.MediTrackApplication
import androidx.compose.ui.platform.LocalContext
import com.example.meditrack.util.NotificationScheduler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditMedicationScreen(medicationId: Int?, onSaveClick: () -> Unit) {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as MediTrackApplication).container
    val medicationViewModel: MedicationViewModel = viewModel(factory = MedicationViewModel.MedicationViewModelFactory(appContainer.medicationRepository))
    val notificationScheduler = remember { NotificationScheduler(context) }

    var name by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }
    var schedule by remember { mutableStateOf("") }

    // Extracted logic for loading medication
    LaunchedEffect(medicationId) {
        loadMedicationIfNeeded(medicationId, medicationViewModel) { med ->
            name = med.name
            dosage = med.dosage
            frequency = med.frequency
            schedule = med.schedule
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(getScreenTitle(medicationId)) })
        }
    ) { paddingValues ->
        MedicationForm(
            name = name,
            onNameChange = { name = it },
            dosage = dosage,
            onDosageChange = { dosage = it },
            frequency = frequency,
            onFrequencyChange = { frequency = it },
            schedule = schedule,
            onScheduleChange = { schedule = it },
            onSaveClick = {
                handleSaveClick(
                    medicationId,
                    name,
                    dosage,
                    frequency,
                    schedule,
                    medicationViewModel,
                    notificationScheduler,
                    onSaveClick
                )
            },
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        )
    }
}

@Composable
private fun MedicationForm(
    name: String,
    onNameChange: (String) -> Unit,
    dosage: String,
    onDosageChange: (String) -> Unit,
    frequency: String,
    onFrequencyChange: (String) -> Unit,
    schedule: String,
    onScheduleChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nome do Medicamento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = dosage,
            onValueChange = onDosageChange,
            label = { Text("Dosagem") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = frequency,
            onValueChange = onFrequencyChange,
            label = { Text("Frequência") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = schedule,
            onValueChange = onScheduleChange,
            label = { Text("Horário (ex: 8h, 14h, 20h)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}

private fun getScreenTitle(medicationId: Int?) =
    if (medicationId == null || medicationId == 0) "Adicionar Medicamento" else "Editar Medicamento"

private suspend fun loadMedicationIfNeeded(
    medicationId: Int?,
    medicationViewModel: MedicationViewModel,
    onLoaded: (Medication) -> Unit
) {
    if (medicationId != null && medicationId != 0) {
        medicationViewModel.getMedicationById(medicationId).collect { medication ->
            medication?.let { onLoaded(it) }
        }
    }
}

private fun handleSaveClick(
    medicationId: Int?,
    name: String,
    dosage: String,
    frequency: String,
    schedule: String,
    medicationViewModel: MedicationViewModel,
    notificationScheduler: NotificationScheduler,
    onSaveClick: () -> Unit
) {
    val medication = Medication(
        id = medicationId ?: 0,
        name = name,
        dosage = dosage,
        frequency = frequency,
        schedule = schedule
    )
    if (medication.isValid()) {
        if (medicationId == null || medicationId == 0) {
            medicationViewModel.insertMedication(medication)
        } else {
            medicationViewModel.updateMedication(medication)
        }
        notificationScheduler.scheduleNotification(medication)
        onSaveClick()
    } else {
        val context = notificationScheduler.context
        android.widget.Toast.makeText(context, "Por favor, preencha todos os campos corretamente.", android.widget.Toast.LENGTH_SHORT).show()
    }
}
