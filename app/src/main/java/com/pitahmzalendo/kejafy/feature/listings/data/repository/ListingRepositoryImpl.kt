package com.pitahmzalendo.kejafy.feature.listings.data.repository

import com.pitahmzalendo.kejafy.feature.listings.data.mapper.toDomain
import com.pitahmzalendo.kejafy.feature.listings.data.remote.ListingApi
import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.ListingFeedItemDto
import com.pitahmzalendo.kejafy.feature.listings.domain.model.ListingFilters
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import com.pitahmzalendo.kejafy.feature.listings.domain.repository.ListingRepository

class ListingRepositoryImpl(
    private val api: ListingApi
) : ListingRepository {

    override suspend fun getListing(id: Long): Result<PropertyListing> {
        return runCatching {
            api.getListing(id).toDomain()
        }
    }

    override suspend fun getRecommendedListings(cursor: String?): Result<PagedResult<PropertyListing>> {
        return runCatching {
            api.getRecommendedListings(cursor).toDomain { it.toDomain() }
        }
    }

    override suspend fun searchListings(
        filters: ListingFilters,
        cursor: String?
    ): Result<PagedResult<PropertyListing>> {
        return runCatching {
            api.searchListings(
                purpose = filters.listingType?.let { "FOR_$it" },
                propertyTypes = filters.propertyTypes.map { it.name },
                minPrice = filters.minPrice,
                maxPrice = filters.maxPrice,
                bedrooms = filters.bedrooms,
                bathrooms = filters.bathrooms,
                latitude = filters.location?.latitude,
                longitude = filters.location?.longitude,
                radiusKm = filters.location?.radiusKm,
                cursor = cursor
            ).toDomain { it.toDomain() }
        }
    }
}
