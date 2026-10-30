package com.example.citypulse

class RemindingFavoritesRepository(
    private val delegate: FavoritesRepository,
    private val scheduler: ReminderScheduler
) : FavoritesRepository by delegate {
    override suspend fun add(event: Event) {
        delegate.add(event)
        scheduler.schedule(event)
    }

    override suspend fun remove(eventId: Int) {
        delegate.remove(eventId)
        scheduler.cancel(eventId)
    }
}
