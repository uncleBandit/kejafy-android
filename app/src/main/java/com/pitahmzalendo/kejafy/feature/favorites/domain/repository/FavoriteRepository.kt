package com.pitahmzalendo.kejafy.feature.favorites.domain.repository

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<PropertyListing>>
    suspend fun toggleFavorite(listingId: Long): Result<Unit>
    fun isFavorite(listingId: Long): Flow<Boolean>
}
