package com.example.citypulse

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeFavoritesRepository(
    initial: List<Event> = emptyList()
) : FavoritesRepository {
    private val saved = MutableStateFlow(initial)
    override val favorites: Flow<List<Event>> = saved

    override suspend fun add(event: Event) {
        saved.update { list -> list.filter { it.id != event.id } + event }
    }

    override suspend fun remove(eventId: Int) {
        saved.update { list -> list.filter { it.id != eventId } }
    }
}
