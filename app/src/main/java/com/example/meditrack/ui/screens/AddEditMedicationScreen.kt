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

    LaunchedEffect(medicationId) {
        if (medicationId != null && medicationId != 0) {
            medicationViewModel.getMedicationById(medicationId).collect { medication ->
                medication?.let {
                    name = it.name
                    dosage = it.dosage
                    frequency = it.frequency
                    schedule = it.schedule
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (medicationId == null || medicationId == 0) "Adicionar Medicamento" else "Editar Medicamento") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do Medicamento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = dosage,
                onValueChange = { dosage = it },
                label = { Text("Dosagem") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = frequency,
                onValueChange = { frequency = it },
                label = { Text("Frequência") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = schedule,
                onValueChange = { schedule = it },
                label = { Text("Horário (ex: 8h, 14h, 20h)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
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
                        // TODO: Show error message to user
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
        }
    }
}

