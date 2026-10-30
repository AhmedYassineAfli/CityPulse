package com.example.citypulse

import org.junit.Assert.assertEquals
import org.junit.Test

class FavoriteMapperTest {
    @Test
    fun eventVersEntiteEtRetour_conserveTousLesChamps() {
        val event = Event(
            7, "Nuit du jazz", "Concert", "Ven. 10 oct. · 20h30",
            "Théâtre municipal", "https://exemple.org/jazz.png",
            isFavorite = true, description = "Une soirée de jazz."
        )
        assertEquals(event, event.toEntity().toEvent())
    }
}
