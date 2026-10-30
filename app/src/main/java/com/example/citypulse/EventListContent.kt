package com.example.citypulse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EventListContent(
    uiState: EventsUiState,
    onCategorySelected: (String?) -> Unit,
    onToggleFavorite: (Int) -> Unit,
    onEventClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
    selectedEventId: Int? = null
) {
    var showBanner by rememberSaveable { mutableStateOf(true) }
    val categories = listOf("Concert", "Expo", "Sport")

    Column(modifier = modifier) {
        // Bannière « À la une » animée et accessible
        AnimatedVisibility(
            visible = showBanner && uiState.events.isNotEmpty(),
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            uiState.events.firstOrNull()?.let { featured ->
                FeaturedBanner(
                    event = featured,
                    onDismiss = { showBanner = false },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        // Puces de filtres par catégorie avec défilement horizontal
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CategoryChip(
                label = "Tous",
                selected = uiState.selectedCategory == null,
                onClick = { onCategorySelected(null) }
            )
            categories.forEach { cat ->
                CategoryChip(
                    label = cat,
                    selected = uiState.selectedCategory == cat,
                    onClick = { onCategorySelected(cat) }
                )
            }
        }

        // Gestion des états selon l'Atelier 11
        val loadState = uiState.loadState
        when {
            loadState is LoadState.Loading -> LoadingState()
            loadState is LoadState.Error && uiState.events.isEmpty() ->
                ErrorState(message = loadState.message, onRetry = onRetry)
            uiState.visibleEvents.isEmpty() -> EmptyState()
            else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (loadState is LoadState.Error) {
                    item(key = "offline") { OfflineBanner(onRetry = onRetry) }
                }
                items(
                    items = uiState.visibleEvents,
                    key = { it.id }
                ) { event ->
                    EventCard(
                        event = event,
                        onClick = { onEventClick(event.id) },
                        onFavoriteClick = { onToggleFavorite(event.id) },
                        isSelected = event.id == selectedEventId
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.semantics {
                contentDescription = "Chargement des événements"
            }
        )
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Réessayer")
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Aucun événement pour le moment.", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
