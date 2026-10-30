package com.example.citypulse

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

/**
 * Fonction pure déterminant si l'écran doit afficher deux volets (Atelier 9).
 * - Compact (< 600 dp) : faux (un seul volet avec navigation)
 * - Medium (600 à 839 dp) : vrai (deux volets 45% / 55%)
 * - Expanded (>= 840 dp) : vrai (deux volets 45% / 55%)
 */
fun shouldShowTwoPanes(widthSizeClass: WindowWidthSizeClass): Boolean =
    widthSizeClass == WindowWidthSizeClass.Medium || widthSizeClass == WindowWidthSizeClass.Expanded
