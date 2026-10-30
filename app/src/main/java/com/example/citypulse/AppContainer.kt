package com.example.citypulse

import android.content.Context
import androidx.room.Room
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val eventsRepository: EventsRepository
    val favoritesRepository: FavoritesRepository
    val preferencesRepository: PreferencesRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val appContext = context.applicationContext

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val apiService: EventsApiService by lazy {
        retrofit.create(EventsApiService::class.java)
    }

    override val eventsRepository: EventsRepository by lazy {
        NetworkEventsRepository(apiService)
    }

    private val database: CityPulseDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            CityPulseDatabase::class.java,
            "citypulse.db"
        ).build()
    }

    override val favoritesRepository: FavoritesRepository by lazy {
        RemindingFavoritesRepository(
            delegate = RoomFavoritesRepository(database.favoriteEventDao()),
            scheduler = WorkManagerReminderScheduler(appContext)
        )
    }

    override val preferencesRepository: PreferencesRepository by lazy {
        DataStorePreferencesRepository(appContext.settingsDataStore)
    }
}
