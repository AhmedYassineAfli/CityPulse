package com.example.citypulse

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ReminderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        val eventId = inputData.getInt(KEY_EVENT_ID, -1)
        val title = inputData.getString(KEY_TITLE)
        if (eventId < 0 || title == null) return Result.failure()

        val manager = NotificationManagerCompat.from(applicationContext)
        if (!manager.areNotificationsEnabled()) return Result.success()

        val openApp = PendingIntent.getActivity(
            applicationContext,
            eventId,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(
            applicationContext,
            Reminders.CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Bientôt : $title")
            .setContentText("Votre événement favori approche.")
            .setContentIntent(openApp)
            .setAutoCancel(true)
            .build()

        manager.notify(eventId, notification)
        return Result.success()
    }

    companion object {
        const val KEY_EVENT_ID = "event_id"
        const val KEY_TITLE = "title"
    }
}
