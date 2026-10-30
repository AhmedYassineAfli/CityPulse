package com.example.citypulse

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val CityPulseDarkColorScheme = darkColorScheme(
    primary = Color(0xFF7FA8FF),
    onPrimary = Color(0xFF0B1A33),
    secondary = Color(0xFFFF8A70),
    background = Color(0xFF121821),
    surface = Color(0xFF1B2430),
    onBackground = Color(0xFFE8EDF2),
    onSurface = Color(0xFFE8EDF2),
    onSurfaceVariant = Color(0xFFB5C0C4),
    outlineVariant = Color(0xFF324155)
)

val CityPulseLightColorScheme = lightColorScheme(
    primary = Color(0xFF2D6CDF),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFF6B4A),
    background = Color(0xFFF5F7FA),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1A1A2E),
    onSurface = Color(0xFF1A1A2E),
    onSurfaceVariant = Color(0xFF52656B),
    outlineVariant = Color(0xFFC9D2E0)
)

val CityPulseTypography = Typography(
    titleLarge = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontSize = 14.sp)
)

@Composable
fun CityPulseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) CityPulseDarkColorScheme else CityPulseLightColorScheme,
        typography = CityPulseTypography,
        content = content
    )
}
