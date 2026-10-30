package com.example.citypulse

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStorePreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {
    private object Keys {
        val DEFAULT_CATEGORY = stringPreferencesKey("default_category")
        val THEME_MODE = stringPreferencesKey("theme_mode")
    }

    override val preferences: Flow<UserPreferences> = dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs ->
            UserPreferences(
                defaultCategory = prefs[Keys.DEFAULT_CATEGORY],
                themeMode = prefs[Keys.THEME_MODE]
                    ?.let { runCatching { ThemeMode.valueOf(it) }.getOrNull() }
                    ?: ThemeMode.SYSTEM
            )
        }

    override suspend fun setDefaultCategory(category: String?) {
        dataStore.edit { prefs ->
            if (category == null) prefs.remove(Keys.DEFAULT_CATEGORY)
            else prefs[Keys.DEFAULT_CATEGORY] = category
        }
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { prefs ->
            prefs[Keys.THEME_MODE] = mode.name
        }
    }
}
