package com.example.citypulse

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakePreferencesRepository(
    initial: UserPreferences = UserPreferences()
) : PreferencesRepository {
    private val saved = MutableStateFlow(initial)
    override val preferences: Flow<UserPreferences> = saved

    override suspend fun setDefaultCategory(category: String?) {
        saved.update { it.copy(defaultCategory = category) }
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        saved.update { it.copy(themeMode = mode) }
    }
}
