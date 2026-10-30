package com.example.citypulse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Écran adaptatif à deux volets pour grand écran (Ateliers 9, 10 & 11).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListDetailScreen(
    uiState: EventsUiState,
    onCategorySelected: (String?) -> Unit,
    onToggleFavorite: (Int) -> Unit,
    onEventClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
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
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            EventListContent(
                uiState = uiState,
                onCategorySelected = onCategorySelected,
                onToggleFavorite = onToggleFavorite,
                onEventClick = onEventClick,
                onRetry = onRetry,
                selectedEventId = uiState.selectedEventId,
                modifier = Modifier.weight(0.45f)
            )

            // Séparateur vertical fin accessible avec le thème
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.outlineVariant)
            )

            // Volet de détail
            Box(
                modifier = Modifier
                    .weight(0.55f)
                    .fillMaxHeight()
            ) {
                val event = uiState.selectedEvent
                if (event != null) {
                    EventDetailContent(
                        event = event,
                        onToggleFavorite = { onToggleFavorite(event.id) }
                    )
                } else {
                    DetailPlaceholder()
                }
            }
        }
    }
}

@Composable
fun DetailPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Sélectionnez un événement pour voir son détail",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp)
        )
    }
}
