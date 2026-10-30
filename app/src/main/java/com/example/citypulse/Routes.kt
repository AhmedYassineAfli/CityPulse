package com.example.citypulse

/**
 * Définition centralisée des routes de navigation (Atelier 7).
 */
object Routes {
    const val LIST = "events"
    const val DETAIL = "events/{eventId}"
    fun detail(eventId: Int) = "events/$eventId"
}
