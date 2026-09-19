package com.pitahmzalendo.kejafy.feature.favorites.data.repository

import com.pitahmzalendo.kejafy.core.database.FavoriteDao
import com.pitahmzalendo.kejafy.core.database.FavoriteEntity
import com.pitahmzalendo.kejafy.feature.favorites.domain.repository.FavoriteRepository
import com.pitahmzalendo.kejafy.feature.listings.data.remote.ListingApi
import com.pitahmzalendo.kejafy.feature.listings.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

class FavoriteRepositoryImpl(
    private val dao: FavoriteDao,
    private val api: ListingApi
) : FavoriteRepository {

    override fun getFavorites(): Flow<List<PropertyListing>> {
        return dao.getAllFavorites().map { entities ->
            entities.map { entity ->
                PropertyListing(
                    id = entity.listingId,
                    title = entity.title,
                    description = null,
                    listingType = ListingType.RENT,
                    propertyType = PropertyType.APARTMENT,
                    price = Money(BigDecimal(entity.price.replace(Regex("[^0-9.]"), "")), "KES"),
                    location = PropertyLocation(0.0, 0.0, neighbourhood = entity.neighbourhood),
                    bedrooms = null,
                    bathrooms = null,
                    amenities = emptyList(),
                    media = emptyList(),
                    environmentScore = null,
                    agent = null
                )
            }
        }
    }

    override suspend fun toggleFavorite(listingId: Long): Result<Unit> {
        // In a real app, you'd fetch the listing details first or pass the entity
        // For now, we'll implement the sync logic
        return Result.success(Unit) 
    }

    override fun isFavorite(listingId: Long): Flow<Boolean> {
        return dao.isFavorite(listingId)
    }
    
    // Additional methods for syncing with backend Like API can be added here
}
