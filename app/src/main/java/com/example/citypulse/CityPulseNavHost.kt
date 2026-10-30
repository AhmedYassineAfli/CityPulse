package com.example.citypulse

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun CityPulseNavHost(
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    preferences: UserPreferences,
    onDefaultCategoryChange: (String?) -> Unit,
    onThemeModeChange: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: EventsViewModel = viewModel(factory = EventsViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    var showSettings by rememberSaveable { mutableStateOf(false) }

    if (showSettings) {
        BackHandler { showSettings = false }
        SettingsScreen(
            preferences = preferences,
            onDefaultCategoryChange = onDefaultCategoryChange,
            onThemeModeChange = onThemeModeChange,
            onBack = { showSettings = false },
            modifier = modifier
        )
    } else if (shouldShowTwoPanes(widthSizeClass)) {
        EventListDetailScreen(
            uiState = uiState,
            onCategorySelected = viewModel::onCategorySelected,
            onToggleFavorite = viewModel::onToggleFavorite,
            onEventClick = viewModel::onEventSelected,
            onRetry = viewModel::loadEvents,
            onSettingsClick = { showSettings = true },
            modifier = modifier
        )
    } else {
        NavHost(
            navController = navController,
            startDestination = Routes.LIST,
            modifier = modifier
        ) {
            composable(Routes.LIST) {
                EventListScreen(
                    uiState = uiState,
                    onCategorySelected = viewModel::onCategorySelected,
                    onToggleFavorite = viewModel::onToggleFavorite,
                    onEventClick = { id ->
                        viewModel.onEventSelected(id)
                        navController.navigate(Routes.detail(id))
                    },
                    onRetry = viewModel::loadEvents,
                    onSettingsClick = { showSettings = true }
                )
            }
            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("eventId") { type = NavType.IntType })
            ) { backStackEntry ->
                val eventId = backStackEntry.arguments?.getInt("eventId")
                val event = uiState.events.firstOrNull { it.id == eventId }
                if (event != null) {
                    EventDetailScreen(
                        event = event,
                        onToggleFavorite = { viewModel.onToggleFavorite(event.id) },
                        onBack = { navController.popBackStack() }
                    )
                } else {
                    Text("Événement introuvable")
                }
            }
        }
    }
}
