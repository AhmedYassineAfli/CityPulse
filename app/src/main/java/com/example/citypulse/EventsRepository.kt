package com.example.citypulse

interface EventsRepository {
    suspend fun getEvents(): List<Event>
}

class NetworkEventsRepository(
    private val api: EventsApiService
) : EventsRepository {
    override suspend fun getEvents(): List<Event> =
        api.getEvents().map { it.toEvent() }
}
