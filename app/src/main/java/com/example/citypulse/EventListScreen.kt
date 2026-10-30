package com.example.citypulse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Écran complet de la liste des événements CityPulse (Ateliers 8, 10 & 11).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(
    uiState: EventsUiState,
    onCategorySelected: (String?) -> Unit,
    onToggleFavorite: (Int) -> Unit,
    onEventClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CityPulse",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                actions = {
                    Text(
                        text = "${uiState.favoriteCount} favoris",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Préférences",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        EventListContent(
            uiState = uiState,
            onCategorySelected = onCategorySelected,
            onToggleFavorite = onToggleFavorite,
            onEventClick = onEventClick,
            onRetry = onRetry,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EventListScreenPreview() {
    CityPulseTheme {
        EventListScreen(
            uiState = EventsUiState(loadState = LoadState.Success),
            onCategorySelected = {},
            onToggleFavorite = {},
            onEventClick = {},
            onSettingsClick = {}
        )
    }
}
