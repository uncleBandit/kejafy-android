package com.pitahmzalendo.kejafy.feature.listings.domain.usecase

import com.pitahmzalendo.kejafy.feature.listings.domain.model.ListingFilters
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import com.pitahmzalendo.kejafy.feature.listings.domain.repository.ListingRepository

class SearchListingsUseCase(private val repository: ListingRepository) {
    suspend operator fun invoke(
        filters: ListingFilters,
        cursor: String? = null
    ): Result<PagedResult<PropertyListing>> = repository.searchListings(filters, cursor)
}
