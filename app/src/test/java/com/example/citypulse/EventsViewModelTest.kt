package com.example.citypulse

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.IOException

/**
 * Tests unitaires pour EventsViewModel (Ateliers 8, 9, 10 & 11).
 */
class EventsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val events = listOf(
        Event(1, "Concert A", "Concert", "", "", ""),
        Event(2, "Expo B", "Expo", "", "", ""),
        Event(3, "Sport C", "Sport", "", "", ""),
        Event(4, "Concert D", "Concert", "", "", "")
    )

    private fun newViewModel(
        repo: EventsRepository = FakeEventsRepository(events),
        favRepo: FavoritesRepository = FakeFavoritesRepository(),
        prefsRepo: PreferencesRepository = FakePreferencesRepository()
    ) = EventsViewModel(repo, favRepo, prefsRepo)

    @Test
    fun etatInitial_afficheToutSansFavori() {
        val vm = newViewModel()
        val state = vm.uiState.value
        assertNull(state.selectedCategory)
        assertEquals(4, state.visibleEvents.size)
        assertEquals(0, state.favoriteCount)
    }

    @Test
    fun chargementReussi_publieLesEvenementsEtLEtatSuccess() {
        val state = newViewModel().uiState.value
        assertEquals(LoadState.Success, state.loadState)
        assertEquals(4, state.events.size)
    }

    @Test
    fun echecReseau_publieUnEtatErreur() {
        val vm = newViewModel(FakeEventsRepository(failure = IOException()))
        assertTrue(vm.uiState.value.loadState is LoadState.Error)
    }

    @Test
    fun reessayerApresUneErreur_chargeLesEvenements() {
        val repo = FakeEventsRepository(events, failure = IOException())
        val vm = newViewModel(repo)
        repo.failure = null
        vm.loadEvents()
        assertEquals(LoadState.Success, vm.uiState.value.loadState)
        assertEquals(4, vm.uiState.value.events.size)
    }

    @Test
    fun rechargement_conserveLesFavoris() = runTest {
        val vm = newViewModel()
        vm.onToggleFavorite(2)
        vm.loadEvents()
        assertEquals(1, vm.uiState.value.favoriteCount)
        assertTrue(vm.uiState.value.events.first { it.id == 2 }.isFavorite)
    }

    @Test
    fun selectionnerUneCategorie_neGardeQueSesEvenements() {
        val vm = newViewModel()
        vm.onCategorySelected("Concert")
        assertEquals(listOf(1, 4), vm.uiState.value.visibleEvents.map { it.id })
    }

    @Test
    fun selectionnerNull_reafficheTout() {
        val vm = newViewModel()
        vm.onCategorySelected("Expo")
        vm.onCategorySelected(null)
        assertEquals(4, vm.uiState.value.visibleEvents.size)
    }

    @Test
    fun basculerUnFavori_leMarqueEtIncrementeLeCompteur() = runTest {
        val vm = newViewModel()
        vm.onToggleFavorite(2)
        val state = vm.uiState.value
        assertEquals(1, state.favoriteCount)
        assertTrue(state.events.first { it.id == 2 }.isFavorite)
    }

    @Test
    fun basculerDeuxFois_revientALEtatInitial() = runTest {
        val vm = newViewModel()
        vm.onToggleFavorite(3)
        vm.onToggleFavorite(3)
        assertEquals(0, vm.uiState.value.favoriteCount)
    }

    @Test
    fun changerDeFiltre_conserveLesFavoris() = runTest {
        val vm = newViewModel()
        vm.onToggleFavorite(1) // Concert A en favori
        vm.onCategorySelected("Expo")
        assertEquals(1, vm.uiState.value.favoriteCount)
        assertTrue(vm.uiState.value.events.first { it.id == 1 }.isFavorite)
    }

    @Test
    fun selectionnerUnEvenement_leRendDisponibleDansLEtat() {
        val vm = newViewModel()
        vm.onEventSelected(2)
        assertEquals(2, vm.uiState.value.selectedEvent?.id)
    }

    @Test
    fun deselectionner_remetLaSelectionANull() {
        val vm = newViewModel()
        vm.onEventSelected(2)
        vm.onEventSelected(null)
        assertNull(vm.uiState.value.selectedEvent)
    }

    @Test
    fun favoriAjoute_resteMarqueApresRechargement() = runTest {
        val vm = newViewModel()
        vm.onToggleFavorite(1)
        vm.loadEvents()
        assertTrue(vm.uiState.value.events.first { it.id == 1 }.isFavorite)
        assertEquals(1, vm.uiState.value.favoriteCount)
    }

    @Test
    fun horsLigne_afficheLesFavorisEnregistres() = runTest {
        val saved = Event(5, "Nuit du jazz", "Concert", "", "", "", isFavorite = true)
        val vm = EventsViewModel(
            eventsRepository = FakeEventsRepository(failure = IOException()),
            favoritesRepository = FakeFavoritesRepository(listOf(saved)),
            preferencesRepository = FakePreferencesRepository()
        )
        assertTrue(vm.uiState.value.loadState is LoadState.Error)
        assertEquals(listOf(saved), vm.uiState.value.events)
    }

    @Test
    fun categorieParDefaut_estAppliqueeAuDemarrage() = runTest {
        val vm = EventsViewModel(
            FakeEventsRepository(events),
            FakeFavoritesRepository(),
            FakePreferencesRepository(UserPreferences(defaultCategory = "Expo"))
        )
        assertEquals("Expo", vm.uiState.value.selectedCategory)
    }
}
