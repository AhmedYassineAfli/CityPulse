package com.example.citypulse

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class CityPulseApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            Reminders.CHANNEL_ID,
            "Rappels d'événements",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Rappels avant vos événements favoris"
        }
        getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)
    }
}
