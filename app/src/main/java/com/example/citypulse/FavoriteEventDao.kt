package com.example.citypulse

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteEventDao {
    @Query("SELECT * FROM favorite_events ORDER BY id")
    fun observeAll(): Flow<List<FavoriteEventEntity>>

    @Upsert
    suspend fun upsert(event: FavoriteEventEntity)

    @Query("DELETE FROM favorite_events WHERE id = :eventId")
    suspend fun deleteById(eventId: Int)
}
