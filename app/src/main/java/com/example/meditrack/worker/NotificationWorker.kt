package com.example.meditrack.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.meditrack.R

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val medicationName = inputData.getString("MEDICATION_NAME")
        val medicationDosage = inputData.getString("MEDICATION_DOSAGE")

        if (medicationName != null && medicationDosage != null) {
            showNotification(medicationName, medicationDosage)
        }

        return Result.success()
    }

    private fun showNotification(medicationName: String, medicationDosage: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "meditrack_channel"
        val channelName = "Lembretes de Medicamentos"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal para lembretes de medicamentos"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // TODO: Replace with a proper icon
            .setContentTitle("Hora do seu medicamento!")
            .setContentText("É hora de tomar $medicationName ($medicationDosage).")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}

