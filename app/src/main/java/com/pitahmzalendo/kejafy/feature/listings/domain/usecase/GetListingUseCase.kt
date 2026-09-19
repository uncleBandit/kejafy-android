package com.pitahmzalendo.kejafy.feature.listings.domain.usecase

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import com.pitahmzalendo.kejafy.feature.listings.domain.repository.ListingRepository

class GetListingUseCase(private val repository: ListingRepository) {
    suspend operator fun invoke(id: Long): Result<PropertyListing> = repository.getListing(id)
}
