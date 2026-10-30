package com.example.citypulse

import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingTest {

    @Test
    fun getGreeting_matin_retourneAccrocheMatin() {
        val message = getGreeting(7)
        assertEquals("Bonjour ! Découvrez les événements du jour près de chez vous.", message)
    }

    @Test
    fun getGreeting_apresMidi_retourneAccrocheApresMidi() {
        val message = getGreeting(14)
        assertEquals("Il se passe quelque chose près de chez vous cet après-midi.", message)
    }

    @Test
    fun getGreeting_soir_retourneAccrocheSoir() {
        val message = getGreeting(21)
        assertEquals("Ne manquez plus rien près de chez vous ce soir.", message)
    }

    @Test
    fun getGreeting_nuit_retourneAccrocheSoir() {
        val message = getGreeting(2)
        assertEquals("Ne manquez plus rien près de chez vous ce soir.", message)
    }
}
