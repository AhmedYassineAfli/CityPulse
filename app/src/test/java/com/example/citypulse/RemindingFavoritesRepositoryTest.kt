package com.example.citypulse

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RemindingFavoritesRepositoryTest {
    private class FakeScheduler : ReminderScheduler {
        val scheduled = mutableListOf<Int>()
        val cancelled = mutableListOf<Int>()
        override fun schedule(event: Event) { scheduled += event.id }
        override fun cancel(eventId: Int) { cancelled += eventId }
    }

    private val event = Event(3, "Tournoi", "Sport", "", "", "")

    @Test
    fun ajouterUnFavori_planifieUnRappel() = runTest {
        val scheduler = FakeScheduler()
        val repo = RemindingFavoritesRepository(
            FakeFavoritesRepository(), scheduler
        )
        repo.add(event)
        assertEquals(listOf(3), scheduler.scheduled)
    }

    @Test
    fun retirerUnFavori_annuleLeRappel() = runTest {
        val scheduler = FakeScheduler()
        val repo = RemindingFavoritesRepository(
            FakeFavoritesRepository(listOf(event)), scheduler
        )
        repo.remove(3)
        assertEquals(listOf(3), scheduler.cancelled)
    }
}
