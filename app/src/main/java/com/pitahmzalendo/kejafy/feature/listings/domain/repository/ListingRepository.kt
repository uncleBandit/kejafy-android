package com.pitahmzalendo.kejafy.feature.listings.domain.repository

import com.pitahmzalendo.kejafy.feature.listings.domain.model.ListingFilters
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

interface ListingRepository {

    suspend fun getListing(id: Long): Result<PropertyListing>

    suspend fun getRecommendedListings(
        cursor: String?
    ): Result<PagedResult<PropertyListing>>

    suspend fun searchListings(
        filters: ListingFilters,
        cursor: String?
    ): Result<PagedResult<PropertyListing>>
}
