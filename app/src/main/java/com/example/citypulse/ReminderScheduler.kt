package com.example.citypulse

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

interface ReminderScheduler {
    fun schedule(event: Event)
    fun cancel(eventId: Int)
}

class WorkManagerReminderScheduler(
    context: Context
) : ReminderScheduler {
    private val workManager = WorkManager.getInstance(context.applicationContext)

    override fun schedule(event: Event) {
        val request = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(Reminders.DELAY_SECONDS, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder().setRequiresBatteryNotLow(true).build()
            )
            .setInputData(
                workDataOf(
                    ReminderWorker.KEY_EVENT_ID to event.id,
                    ReminderWorker.KEY_TITLE to event.title
                )
            )
            .build()

        workManager.enqueueUniqueWork(
            workName(event.id), ExistingWorkPolicy.REPLACE, request
        )
    }

    override fun cancel(eventId: Int) {
        workManager.cancelUniqueWork(workName(eventId))
    }

    private fun workName(eventId: Int) = "reminder_$eventId"
}
