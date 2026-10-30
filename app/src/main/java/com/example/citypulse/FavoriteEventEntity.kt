package com.example.citypulse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_events")
data class FavoriteEventEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val location: String,
    val imageUrl: String,
    val description: String
)

// Convertit notre modèle UI/domaine vers l'entité Room
fun Event.toEntity() = FavoriteEventEntity(
    id = id,
    title = title,
    category = category,
    date = date,
    location = location,
    imageUrl = imageUrl,
    description = description
)

// Reconstruit un Event : s'il est dans la table, isFavorite = true
fun FavoriteEventEntity.toEvent() = Event(
    id = id,
    title = title,
    category = category,
    date = date,
    location = location,
    imageUrl = imageUrl,
    isFavorite = true,
    description = description
)
