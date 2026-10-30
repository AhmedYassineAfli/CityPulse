package com.example.citypulse

import kotlinx.coroutines.flow.Flow

enum class ThemeMode { SYSTEM, LIGHT, DARK }

data class UserPreferences(
    val defaultCategory: String? = null,
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)

interface PreferencesRepository {
    val preferences: Flow<UserPreferences>
    suspend fun setDefaultCategory(category: String?)
    suspend fun setThemeMode(mode: ThemeMode)
}
