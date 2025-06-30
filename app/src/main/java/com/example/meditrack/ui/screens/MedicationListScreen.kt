package com.example.meditrack.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditrack.data.model.Medication
import com.example.meditrack.ui.viewmodel.MedicationViewModel
import com.example.meditrack.MediTrackApplication
import androidx.compose.ui.platform.LocalContext
import com.example.meditrack.util.PdfGenerator
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.widget.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationListScreen(
    onAddMedicationClick: () -> Unit, 
    onBulkAddMedicationClick: () -> Unit,
    onMedicationClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as MediTrackApplication).container
    val medicationViewModel: MedicationViewModel = viewModel(factory = MedicationViewModel.MedicationViewModelFactory(appContainer.medicationRepository))
    val medications by medicationViewModel.allMedications.collectAsState(initial = emptyList())
    val pdfGenerator = remember { PdfGenerator(context) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            val file = pdfGenerator.generateMedicationHistoryPdf(medications)
            if (file != null) {
                Toast.makeText(context, "PDF gerado em: ${file.absolutePath}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Erro ao gerar PDF.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Permissão de escrita negada. Não é possível gerar o PDF.", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meus Medicamentos") },
                actions = {
                    IconButton(onClick = {
                        when {
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) == PackageManager.PERMISSION_GRANTED -> {
                                val file = pdfGenerator.generateMedicationHistoryPdf(medications)
                                if (file != null) {
                                    Toast.makeText(context, "PDF gerado em: ${file.absolutePath}", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(context, "Erro ao gerar PDF.", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else -> {
                                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            }
                        }
                    }) {
                        Icon(Icons.Filled.Share, "Exportar Histórico")
                    }
                }
            )
        },
        floatingActionButton = {
            var expanded by remember { mutableStateOf(false) }
            
            Box {
                FloatingActionButton(
                    onClick = { expanded = true }
                ) {
                    Icon(Icons.Filled.Add, "Adicionar Medicamento")
                }
                
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Adicionar 1 Medicamento") },
                        onClick = {
                            expanded = false
                            onAddMedicationClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Adicionar Múltiplos") },
                        onClick = {
                            expanded = false
                            onBulkAddMedicationClick()
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            items(medications) {
                medication ->
                MedicationItem(medication = medication, onMedicationClick = onMedicationClick)
            }
        }
        if (medications.isEmpty()) {
            Text("Nenhum medicamento cadastrado. Clique no + para adicionar.", modifier = Modifier.fillMaxSize().wrapContentSize())
        }
    }
}

@Composable
fun MedicationItem(medication: Medication, onMedicationClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMedicationClick(medication.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = medication.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Dosagem: ${medication.dosage}")
            Text(text = "Frequência: ${medication.frequency}")
            Text(text = "Horário: ${medication.schedule}")
            Text(text = "Status: ${medication.status}")
        }
    }
}

