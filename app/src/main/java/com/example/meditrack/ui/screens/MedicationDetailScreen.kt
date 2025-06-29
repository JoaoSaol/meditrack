
package com.example.meditrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditrack.ui.viewmodel.MedicationViewModel
import com.example.meditrack.MediTrackApplication
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationDetailScreen(medicationId: Int, onBackClick: () -> Unit) {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as MediTrackApplication).container
    val medicationViewModel: MedicationViewModel = viewModel(factory = MedicationViewModel.MedicationViewModelFactory(appContainer.medicationRepository))

    val medication by medicationViewModel.getMedicationById(medicationId).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(medication?.name ?: "Detalhes do Medicamento") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            medication?.let {
                Text(text = "Nome: ${it.name}")
                Text(text = "Dosagem: ${it.dosage}")
                Text(text = "Frequência: ${it.frequency}")
                Text(text = "Horário: ${it.schedule}")
                Text(text = "Status: ${it.status}")
                Spacer(modifier = Modifier.height(16.dp))
                if (it.status != "Tomado") {
                    Button(onClick = {
                        medicationViewModel.markMedicationAsTaken(it)
                        onBackClick()
                    }) {
                        Text("Marcar como Tomado")
                    }
                }
            } ?: run {
                Text("Medicamento não encontrado.")
            }
        }
    }
}

