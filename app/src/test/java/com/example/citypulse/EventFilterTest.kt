package com.example.citypulse

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests unitaires pour la fonction filterEvents (Atelier 4).
 */
class EventFilterTest {

    private val sampleEvents = listOf(
        Event(1, "Concert acoustique au parc", "Concert", "", "", ""),
        Event(2, "Exposition photo urbaine", "Expo", "", "", ""),
        Event(3, "Tournoi de basket 3x3", "Sport", "", "", "")
    )

    @Test
    fun categorieExistante_neRetourneQueLesEvenementsCorrespondants() {
        val result = filterEvents(sampleEvents, "Concert")
        assertEquals(1, result.size)
        assertEquals("Concert", result[0].category)
    }

    @Test
    fun categorieNull_retourneTousLesEvenements() {
        val result = filterEvents(sampleEvents, null)
        assertEquals(3, result.size)
    }

    @Test
    fun categorieAbsente_retourneUneListeVide() {
        val result = filterEvents(sampleEvents, "Théâtre")
        assertEquals(0, result.size)
    }

    @Test
    fun listeVide_retourneUneListeVide() {
        val result = filterEvents(emptyList(), "Concert")
        assertEquals(0, result.size)
    }

    @Test
    fun categorieInsensibleALaCasse_retourneEvenements() {
        val result = filterEvents(sampleEvents, "concert")
        assertEquals(1, result.size)
    }
}
