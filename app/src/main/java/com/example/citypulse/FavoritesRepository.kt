package com.example.citypulse

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FavoritesRepository {
    val favorites: Flow<List<Event>>
    suspend fun add(event: Event)
    suspend fun remove(eventId: Int)
}

class RoomFavoritesRepository(
    private val dao: FavoriteEventDao
) : FavoritesRepository {
    override val favorites: Flow<List<Event>> =
        dao.observeAll().map { list -> list.map { it.toEvent() } }

    override suspend fun add(event: Event) = dao.upsert(event.toEntity())

    override suspend fun remove(eventId: Int) = dao.deleteById(eventId)
}
