package com.example.citypulse

/**
 * Modèle de données pour un événement CityPulse (Atelier 10).
 * - Atelier 2 : champs de base (id, title, category, date, location)
 * - Atelier 4 : isFavorite (avec valeur par défaut false)
 * - Atelier 7 : description (avec valeur par défaut "")
 * - Atelier 10 : imageUrl (adresse distante de l'image)
 */
data class Event(
    val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val location: String,
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val description: String = ""
)
