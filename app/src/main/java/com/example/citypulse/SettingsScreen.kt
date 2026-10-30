package com.example.citypulse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    preferences: UserPreferences,
    onDefaultCategoryChange: (String?) -> Unit,
    onThemeModeChange: (ThemeMode) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Préférences") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding).padding(16.dp)) {
            SectionTitle("Catégorie affichée au démarrage")
            Column(Modifier.selectableGroup()) {
                OptionRow("Toutes", preferences.defaultCategory == null) {
                    onDefaultCategoryChange(null)
                }
                listOf("Concert", "Expo", "Sport").forEach { cat ->
                    OptionRow(cat, preferences.defaultCategory == cat) {
                        onDefaultCategoryChange(cat)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            SectionTitle("Thème")
            Column(Modifier.selectableGroup()) {
                OptionRow(
                    "Suivre le système",
                    preferences.themeMode == ThemeMode.SYSTEM
                ) {
                    onThemeModeChange(ThemeMode.SYSTEM)
                }
                OptionRow("Clair", preferences.themeMode == ThemeMode.LIGHT) {
                    onThemeModeChange(ThemeMode.LIGHT)
                }
                OptionRow("Sombre", preferences.themeMode == ThemeMode.DARK) {
                    onThemeModeChange(ThemeMode.DARK)
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .semantics { heading() }
    )
}

@Composable
private fun OptionRow(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .selectable(
                selected = selected,
                onClick = onSelect,
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null)
        Spacer(Modifier.width(12.dp))
        Text(label)
    }
}
