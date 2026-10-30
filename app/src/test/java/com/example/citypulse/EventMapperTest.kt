package com.example.citypulse

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class EventMapperTest {
    @Test
    fun toEvent_construitLAdresseCompleteDeLImage() {
        val dto = EventDto(1, "T", "Concert", "d", "l", "desc", "a.png")
        val event = dto.toEvent(imagesUrl = "https://exemple.org/images/")
        assertEquals("https://exemple.org/images/a.png", event.imageUrl)
        assertFalse(event.isFavorite)
    }
}
