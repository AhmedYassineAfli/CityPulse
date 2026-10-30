package com.example.citypulse

import androidx.compose.ui.graphics.Color

/**
 * Palette accessible conforme WCAG (Atelier 6).
 * 4.5:1 minimum pour le texte, 3:1 pour les éléments graphiques.
 */
val Blue = Color(0xFF2D6CDF)
val BlueDark = Color(0xFF1B4FAE)
val ScreenBackground = Color(0xFFF5F7FA)
val TextOnLight = Color(0xFF1A1A2E)
val TextSecondary = Color(0xFF52656B)
val FavoriteActive = Color(0xFFE0482A)
val FavoriteInactive = Color(0xFF6B7C82)
val CategoryConcert = Color(0xFF1B4FAE)
val CategoryExpo = Color(0xFF8A3FA0)
val CategorySport = Color(0xFF1E7B4A)
val BannerBackground = Color(0xFFE3FBEF)
val BannerText = Color(0xFF1B4FAE)

/**
 * Retourne la couleur accessible associée à une catégorie d'événement.
 */
fun categoryColor(category: String): Color = when (category) {
    "Concert" -> CategoryConcert
    "Expo" -> CategoryExpo
    "Sport" -> CategorySport
    else -> BlueDark
}
