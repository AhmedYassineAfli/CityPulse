package com.example.citypulse

/**
 * Type scellé représentant l'état de chargement réseau (Atelier 10).
 */
sealed interface LoadState {
    object Loading : LoadState
    object Success : LoadState
    data class Error(val message: String) : LoadState
}

data class EventsUiState(
    val remoteEvents: List<Event> = emptyList(),
    val savedFavorites: List<Event> = emptyList(),
    val selectedCategory: String? = null,
    val selectedEventId: Int? = null,
    val loadState: LoadState = LoadState.Loading
) {
    /** Réseau + marque « favori » issue de Room ; sinon, les favoris seuls. */
    val events: List<Event> =
        if (loadState is LoadState.Success) {
            val ids = savedFavorites.map { it.id }.toSet()
            remoteEvents.map { it.copy(isFavorite = it.id in ids) }
        } else {
            savedFavorites
        }

    val visibleEvents: List<Event>
        get() = filterEvents(events, selectedCategory)

    val favoriteCount: Int
        get() = savedFavorites.size

    val selectedEvent: Event?
        get() = events.firstOrNull { it.id == selectedEventId }
}
