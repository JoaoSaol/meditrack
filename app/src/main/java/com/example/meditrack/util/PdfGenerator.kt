package com.example.meditrack.util

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import com.example.meditrack.data.model.Medication
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfGenerator(private val context: Context) {

    fun generateMedicationHistoryPdf(medications: List<Medication>): File? {
        val pdfDocument = PrintedPdfDocument(context, PrintAttributes.Builder().build())

        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas

        var y = 50f
        val x = 50f
        val lineHeight = 20f

        canvas.drawText("Histórico de Medicamentos", x, y, android.graphics.Paint().apply { textSize = 24f })
        y += 40f

        medications.forEach {
            canvas.drawText("Nome: ${it.name}", x, y, android.graphics.Paint())
            y += lineHeight
            canvas.drawText("Dosagem: ${it.dosage}", x, y, android.graphics.Paint())
            y += lineHeight
            canvas.drawText("Frequência: ${it.frequency}", x, y, android.graphics.Paint())
            y += lineHeight
            canvas.drawText("Horário: ${it.schedule}", x, y, android.graphics.Paint())
            y += lineHeight
            canvas.drawText("Status: ${it.status}", x, y, android.graphics.Paint())
            y += lineHeight * 2
        }

        pdfDocument.finishPage(page)

        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadsDir, "historico_medicamentos.pdf")

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            pdfDocument.close()
        }
        return null
    }
}

