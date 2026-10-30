package com.example.citypulse

/**
 * Fonction pure de filtrage des événements selon la catégorie (Atelier 4).
 * - Si category est null -> retourne tous les événements
 * - Sinon -> filtre selon it.category == category
 * Couverte par des tests unitaires JUnit dans EventFilterTest.kt.
 */
fun filterEvents(events: List<Event>, category: String?): List<Event> {
    return if (category == null) {
        events
    } else {
        events.filter { it.category.equals(category, ignoreCase = true) }
    }
}
