package com.example.meditrack.util

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Data
import com.example.meditrack.data.model.Medication
import com.example.meditrack.worker.NotificationWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

class NotificationScheduler(private val context: Context) {

    fun scheduleNotification(medication: Medication) {
        val workManager = WorkManager.getInstance(context)

        // Cancel any existing work for this medication to avoid duplicates
        workManager.cancelUniqueWork("medication_notification_${medication.id}")

        val scheduleTimes = medication.schedule.split(",").map { it.trim() }

        scheduleTimes.forEachIndexed { index, timeString ->
            val hour = timeString.substringBefore("h").toIntOrNull()
            if (hour != null) {
                val currentTime = Calendar.getInstance()
                val dueTime = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }

                if (dueTime.before(currentTime)) {
                    dueTime.add(Calendar.DAY_OF_MONTH, 1)
                }

                val initialDelay = dueTime.timeInMillis - currentTime.timeInMillis

                val inputData = Data.Builder()
                    .putString("MEDICATION_NAME", medication.name)
                    .putString("MEDICATION_DOSAGE", medication.dosage)
                    .build()

                val notificationWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(repeatInterval = 1, repeatIntervalTimeUnit = TimeUnit.DAYS)
                    .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                    .setInputData(inputData)
                    .addTag("medication_notification_${medication.id}")
                    .build()

                workManager.enqueueUniquePeriodicWork(
                    "medication_notification_${medication.id}_$index",
                    ExistingPeriodicWorkPolicy.UPDATE,
                    notificationWorkRequest
                )
            }
        }
    }
}

