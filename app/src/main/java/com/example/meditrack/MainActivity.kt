package com.example.meditrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meditrack.ui.screens.AddEditMedicationScreen
import com.example.meditrack.ui.screens.BulkAddMedicationScreen
import com.example.meditrack.ui.screens.MedicationDetailScreen
import com.example.meditrack.ui.screens.MedicationListScreen
import com.example.meditrack.ui.theme.MediTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediTrackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "medicationList") {
                        composable("medicationList") {
                            MedicationListScreen(
                                onAddMedicationClick = { navController.navigate("addEditMedication/0") },
                                onBulkAddMedicationClick = { navController.navigate("bulkAddMedication") },
                                onMedicationClick = { medicationId -> navController.navigate("medicationDetail/$medicationId") }
                            )
                        }
                        composable(
                            "addEditMedication/{medicationId}",
                            arguments = listOf(navArgument("medicationId") { type = NavType.IntType })
                        ) {
                            val medicationId = it.arguments?.getInt("medicationId")
                            AddEditMedicationScreen(medicationId = medicationId) {
                                navController.popBackStack()
                            }
                        }
                        composable("bulkAddMedication") {
                            BulkAddMedicationScreen {
                                navController.popBackStack()
                            }
                        }
                        composable(
                            "medicationDetail/{medicationId}",
                            arguments = listOf(navArgument("medicationId") { type = NavType.IntType })
                        ) {
                            val medicationId = it.arguments?.getInt("medicationId")
                            MedicationDetailScreen(medicationId = medicationId!!) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}

