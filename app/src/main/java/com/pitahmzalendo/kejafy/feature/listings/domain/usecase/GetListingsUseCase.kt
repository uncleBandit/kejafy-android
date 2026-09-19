package com.pitahmzalendo.kejafy.feature.listings.domain.usecase

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import com.pitahmzalendo.kejafy.feature.listings.domain.repository.ListingRepository

class GetListingsUseCase(private val repository: ListingRepository) {
    suspend operator fun invoke(cursor: String? = null): Result<PagedResult<PropertyListing>> = 
        repository.getRecommendedListings(cursor)
}
