package com.example.citypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class EventsViewModel(
    private val eventsRepository: EventsRepository,
    private val favoritesRepository: FavoritesRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState: StateFlow<EventsUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
        applyDefaultCategory()
        loadEvents()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoritesRepository.favorites.collect { saved ->
                _uiState.update { it.copy(savedFavorites = saved) }
            }
        }
    }

    private fun applyDefaultCategory() {
        viewModelScope.launch {
            val category = preferencesRepository.preferences.first().defaultCategory
            _uiState.update { it.copy(selectedCategory = category) }
        }
    }

    fun loadEvents() {
        _uiState.update { it.copy(loadState = LoadState.Loading) }
        viewModelScope.launch {
            try {
                val remote = eventsRepository.getEvents()
                _uiState.update {
                    it.copy(remoteEvents = remote, loadState = LoadState.Success)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(loadState = LoadState.Error(messageFor(e)))
                }
            }
        }
    }

    fun onToggleFavorite(eventId: Int) {
        val event = _uiState.value.events.firstOrNull { it.id == eventId } ?: return
        viewModelScope.launch {
            if (event.isFavorite) {
                favoritesRepository.remove(eventId)
            } else {
                favoritesRepository.add(event.copy(isFavorite = true))
            }
        }
    }

    fun onCategorySelected(category: String?) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun onEventSelected(eventId: Int?) {
        _uiState.update { it.copy(selectedEventId = eventId) }
    }

    private fun messageFor(e: Exception): String = when (e) {
        is IOException -> "Connexion impossible. Vérifiez votre réseau."
        else -> e.localizedMessage ?: "Erreur réseau inconnue"
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as CityPulseApplication
                EventsViewModel(
                    app.container.eventsRepository,
                    app.container.favoritesRepository,
                    app.container.preferencesRepository
                )
            }
        }
    }
}
