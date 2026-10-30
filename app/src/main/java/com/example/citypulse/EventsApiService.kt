package com.example.citypulse

import retrofit2.http.GET

interface EventsApiService {
    @GET("events.json")
    suspend fun getEvents(): List<EventDto>
}
