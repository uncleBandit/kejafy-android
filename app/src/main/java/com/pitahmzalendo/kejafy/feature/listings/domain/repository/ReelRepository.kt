package com.pitahmzalendo.kejafy.feature.listings.domain.repository

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyReel

interface ReelRepository {

    suspend fun getFeed(
        cursor: String?
    ): Result<PagedResult<PropertyReel>>
}
