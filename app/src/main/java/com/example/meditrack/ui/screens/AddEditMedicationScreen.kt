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

data class MedicationFormState(
    val name: String = "",
    val dosage: String = "",
    val frequency: String = "",
    val schedule: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditMedicationScreen(medicationId: Int?, onSaveClick: () -> Unit) {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as MediTrackApplication).container
    val medicationViewModel: MedicationViewModel = viewModel(factory = MedicationViewModel.MedicationViewModelFactory(appContainer.medicationRepository))
    val notificationScheduler = remember { NotificationScheduler(context) }

    var formState by remember { mutableStateOf(MedicationFormState()) }

    // Extracted logic for loading medication
    LaunchedEffect(medicationId) {
        loadMedicationIfNeeded(medicationId, medicationViewModel) { med ->
            formState = MedicationFormState(
                name = med.name,
                dosage = med.dosage,
                frequency = med.frequency,
                schedule = med.schedule
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(getScreenTitle(medicationId)) })
        }
    ) { paddingValues ->
        MedicationForm(
            formState = formState,
            onFormChange = { formState = it },
            onSaveClick = {
                handleSaveClick(
                    medicationId,
                    formState,
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
    formState: MedicationFormState,
    onFormChange: (MedicationFormState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = formState.name,
            onValueChange = { onFormChange(formState.copy(name = it)) },
            label = { Text("Nome do Medicamento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.dosage,
            onValueChange = { onFormChange(formState.copy(dosage = it)) },
            label = { Text("Dosagem") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.frequency,
            onValueChange = { onFormChange(formState.copy(frequency = it)) },
            label = { Text("Frequência") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.schedule,
            onValueChange = { onFormChange(formState.copy(schedule = it)) },
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
    formState: MedicationFormState,
    medicationViewModel: MedicationViewModel,
    notificationScheduler: NotificationScheduler,
    onSaveClick: () -> Unit
) {
    val medication = Medication(
        id = medicationId ?: 0,
        name = formState.name,
        dosage = formState.dosage,
        frequency = formState.frequency,
        schedule = formState.schedule
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
