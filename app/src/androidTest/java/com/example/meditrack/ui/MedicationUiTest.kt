package com.example.meditrack.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.meditrack.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MedicationUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addMedication_showsInList() {
        // Click on the FloatingActionButton with "Adicionar Medicamento" content description
        composeTestRule.onNodeWithContentDescription("Adicionar Medicamento").performClick()
        
        // Click on single medication option
        composeTestRule.onNodeWithText("Adicionar 1 Medicamento").performClick()

        // Input medication details
        composeTestRule.onNodeWithText("Nome do Medicamento").performTextInput("Ibuprofeno")
        composeTestRule.onNodeWithText("Dosagem").performTextInput("200mg")
        composeTestRule.onNodeWithText("Frequência").performTextInput("6h")
        composeTestRule.onNodeWithText("Horário (ex: 8h, 14h, 20h)").performTextInput("8h, 14h, 20h")

        // Click save button
        composeTestRule.onNodeWithText("Salvar").performClick()

        // Verify if the medication is displayed in the list
        composeTestRule.onNodeWithText("Ibuprofeno").assertIsDisplayed()
    }
    
    @Test
    fun addMultipleMedications_showsInList() {
        // Click on the FloatingActionButton
        composeTestRule.onNodeWithContentDescription("Adicionar Medicamento").performClick()
        
        // Click on multiple medications option
        composeTestRule.onNodeWithText("Adicionar Múltiplos").performClick()
        
        // Fill first medication - use onNodeWithText since there's only one form initially
        composeTestRule.onNodeWithText("Nome do Medicamento").performTextInput("Paracetamol")
        composeTestRule.onAllNodesWithText("Dosagem").onFirst().performTextInput("500mg")
        composeTestRule.onAllNodesWithText("Frequência").onFirst().performTextInput("8h")
        composeTestRule.onNodeWithText("Horário (ex: 8h, 14h, 20h)").performTextInput("8h, 16h")
        
        // Add another medication
        composeTestRule.onNodeWithContentDescription("Adicionar medicamento").performClick()
        
        // Fill second medication - use onLast() for the second form
        composeTestRule.onAllNodesWithText("Nome do Medicamento").onLast().performTextInput("Dipirona")
        composeTestRule.onAllNodesWithText("Dosagem").onLast().performTextInput("500mg")
        composeTestRule.onAllNodesWithText("Frequência").onLast().performTextInput("6h")
        composeTestRule.onAllNodesWithText("Horário (ex: 8h, 14h, 20h)").onLast().performTextInput("6h, 12h, 18h")
        
        // Save all medications
        composeTestRule.onNodeWithText("Salvar Todos").performClick()
        
        // Verify both medications are displayed
        composeTestRule.onNodeWithText("Paracetamol").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dipirona").assertIsDisplayed()
    }
}

