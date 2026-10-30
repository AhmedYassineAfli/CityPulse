package com.example.citypulse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteEventEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CityPulseDatabase : RoomDatabase() {
    abstract fun favoriteEventDao(): FavoriteEventDao
}
