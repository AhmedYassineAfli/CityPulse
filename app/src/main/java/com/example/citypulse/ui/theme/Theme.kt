package com.example.citypulse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Palette officielle CityPulse (Atelier 1 à 5).
 * - Primary: CityPulse Blue (#2D6CDF)
 * - Secondary: Pulse Orange (#FF6B4A)
 * - Background: #F5F7FA
 * - Surface: #FFFFFF
 * - OnBackground / OnSurface: #1A1A2E
 */
val CityPulseColorScheme = lightColorScheme(
    primary = Color(0xFF2D6CDF),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFF6B4A),
    background = Color(0xFFF5F7FA),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1A1A2E),
    onSurface = Color(0xFF1A1A2E)
)

val CityPulseTypography = Typography(
    titleLarge = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontSize = 14.sp)
)

@Composable
fun CityPulseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CityPulseColorScheme,
        typography = CityPulseTypography,
        content = content
    )
}