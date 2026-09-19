package com.pitahmzalendo.kejafy.feature.listings.domain.usecase

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyReel
import com.pitahmzalendo.kejafy.feature.listings.domain.repository.ReelRepository

class GetReelFeedUseCase(private val repository: ReelRepository) {
    suspend operator fun invoke(cursor: String? = null): Result<PagedResult<PropertyReel>> = 
        repository.getFeed(cursor)
}
