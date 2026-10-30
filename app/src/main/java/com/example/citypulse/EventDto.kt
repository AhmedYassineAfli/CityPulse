package com.example.citypulse

import kotlinx.serialization.Serializable

object NetworkConfig {
    const val BASE_URL = "https://raw.githubusercontent.com/AhmedYassineAfli/CityPulse/main/"
    const val IMAGES_URL = BASE_URL + "images/"
}

@Serializable
data class EventDto(
    val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val location: String,
    val description: String = "",
    val image: String
)

fun EventDto.toEvent(imagesUrl: String = NetworkConfig.IMAGES_URL) = Event(
    id = id,
    title = title,
    category = category,
    date = date,
    location = location,
    imageUrl = imagesUrl + image,
    description = description
)
