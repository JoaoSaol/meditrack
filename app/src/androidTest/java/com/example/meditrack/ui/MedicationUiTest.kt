package com.example.meditrack.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
        // Click on the add button
        composeTestRule.onNodeWithText("Adicionar Medicamento").performClick()

        // Input medication details
        composeTestRule.onNodeWithText("Nome do Medicamento").performTextInput("Ibuprofeno")
        composeTestRule.onNodeWithText("Dosagem").performTextInput("200mg")
        composeTestRule.onNodeWithText("Frequência").performTextInput("6h")
        composeTestRule.onNodeWithText("Horário (ex: 8h, 14h, 20h)").performTextInput("8h, 14h, 20h")

        // Click save button
        composeTestRule.onNodeWithText("Salvar").performClick()

        // Verify if the medication is displayed in the list
        composeTestRule.onNodeWithText("Ibuprofeno").assertExists()
    }
}

