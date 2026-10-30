package com.example.citypulse

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests unitaires pour la fonction pure shouldShowTwoPanes (Atelier 9).
 */
class AdaptiveLayoutTest {

    @Test
    fun compact_afficheUnSeulVolet() {
        assertFalse(shouldShowTwoPanes(WindowWidthSizeClass.Compact))
    }

    @Test
    fun medium_afficheDeuxVolets() {
        assertTrue(shouldShowTwoPanes(WindowWidthSizeClass.Medium))
    }

    @Test
    fun expanded_afficheDeuxVolets() {
        assertTrue(shouldShowTwoPanes(WindowWidthSizeClass.Expanded))
    }
}
