package com.pitahmzalendo.kejafy.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val listingId: Long,
    val title: String,
    val neighbourhood: String?,
    val price: String,
    val timestamp: Long = System.currentTimeMillis()
)
